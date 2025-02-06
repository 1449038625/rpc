package com.zbz.rpc.proxy;

import ch.qos.logback.classic.spi.LoggerContextAware;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.config.RpcConfig;
import com.zbz.rpc.constant.ProtocolConstant;
import com.zbz.rpc.constant.RpcConstant;
import com.zbz.rpc.enums.ProtocolMessageSerializerEnum;
import com.zbz.rpc.enums.ProtocolMessageTypeEnum;
import com.zbz.rpc.fault.retry.RetryStrategy;
import com.zbz.rpc.fault.retry.RetryStrategyFactory;
import com.zbz.rpc.fault.tolerant.TolerantStrategy;
import com.zbz.rpc.fault.tolerant.TolerantStrategyFactory;
import com.zbz.rpc.loadbalancer.LoadBalancer;
import com.zbz.rpc.loadbalancer.LoadBalancerFactory;
import com.zbz.rpc.model.RpcRequest;
import com.zbz.rpc.model.RpcResponse;
import com.zbz.rpc.model.ServiceMetaInfo;
import com.zbz.rpc.protocol.ProtocolMessage;
import com.zbz.rpc.protocol.ProtocolMessageDecoder;
import com.zbz.rpc.protocol.ProtocolMessageEncoder;
import com.zbz.rpc.registry.Registry;
import com.zbz.rpc.registry.RegistryFactory;
import com.zbz.rpc.serializer.Serializer;
import com.zbz.rpc.serializer.SerializerFactory;
import com.zbz.rpc.server.tcp.VertxTcpClient;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        RpcResponse rpcResponse;
        try {
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (serviceMetaInfoList.isEmpty()){
                throw new RuntimeException("没有可用的服务");
            }
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            HashMap<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName",rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            rpcResponse = retryStrategy.doRetry(() -> VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo));
            return rpcResponse.getData();
        }catch (Exception e){
            TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
            rpcResponse = tolerantStrategy.doTolerant(new HashMap<>(), e);
            e.printStackTrace();
        }
        return rpcResponse.getData();
    }
}
