package com.zbz.rpc.bootstrap;

import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.config.RegistryConfig;
import com.zbz.rpc.config.RpcConfig;
import com.zbz.rpc.model.ServiceMetaInfo;
import com.zbz.rpc.model.ServiceRegisterInfo;
import com.zbz.rpc.registry.Registry;
import com.zbz.rpc.registry.RegistryFactory;
import com.zbz.rpc.registry.LocalRegistry;
import com.zbz.rpc.server.VertxServer;
import com.zbz.rpc.server.VertxServerFactory;

import java.util.List;

/**
 * Classname: ProviderBootstrap
 * Package: com.zbz.rpc.bootstrap
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 21:06
 * @Version: v1.0
 */
public class ProviderBootstrap {
    /**
     * 初始化
     *
     * @return
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        RpcApplication.init();
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            }catch (Exception e){
                throw new RuntimeException(serviceName +"服务注册失败",e);
            }
        }
        VertxServer vertxServer = VertxServerFactory.getInstance(rpcConfig.getVertxServer());
        vertxServer.doStart(rpcConfig.getServerPort());
    }
}
