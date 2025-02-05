package com.zbz.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.config.RpcConfig;
import com.zbz.rpc.constant.RpcConstant;
import com.zbz.rpc.model.RpcRequest;
import com.zbz.rpc.model.RpcResponse;
import com.zbz.rpc.model.ServiceMetaInfo;
import com.zbz.rpc.registry.Registry;
import com.zbz.rpc.registry.RegistryFactory;
import com.zbz.rpc.serializer.Serializer;
import com.zbz.rpc.serializer.SerializerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Classname: ServiceProxy
 * Package: com.zbz.rpc.proxy
 * Decription:动态代理
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 16:17
 * @Version: v1.0
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        String serviceName = method.getDeclaringClass().getName();
        Serializer serializer = SerializerFactory.getInstance(rpcConfig.getSerializer());
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            byte[] postData = serializer.serialize(rpcRequest);
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (serviceMetaInfoList.isEmpty()){
                throw new RuntimeException("没有可用的服务");
            }
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);
            try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
                    .body(postData)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
