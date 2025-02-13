package com.zbz.example.provider;

import com.zbz.example.common.service.UserService;
import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.bootstrap.ProviderBootstrap;
import com.zbz.rpc.config.RegistryConfig;
import com.zbz.rpc.config.RpcConfig;
import com.zbz.rpc.model.ServiceMetaInfo;
import com.zbz.rpc.model.ServiceRegisterInfo;
import com.zbz.rpc.registry.LocalRegistry;
import com.zbz.rpc.registry.Registry;
import com.zbz.rpc.registry.RegistryFactory;
import com.zbz.rpc.server.VertxServer;
import com.zbz.rpc.server.VertxServerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Classname: EasyProviderExample
 * Package: com.zbz.example.provider
 * Decription:服务启动类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 10:30
 * @Version: v1.0
 */
public class ProviderExample {
    public static void main(String[] args) {
        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);
        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
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
        VertxServer vertxServer = VertxServerFactory.getInstance(rpcConfig.getVertxServer());
        vertxServer.doStart(rpcConfig.getServerPort());
    }
}
