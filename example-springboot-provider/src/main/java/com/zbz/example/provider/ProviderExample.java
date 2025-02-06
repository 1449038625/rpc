package com.zbz.example.provider;

import com.zbz.example.common.service.UserService;
import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.config.RegistryConfig;
import com.zbz.rpc.config.RpcConfig;
import com.zbz.rpc.model.ServiceMetaInfo;
import com.zbz.rpc.registry.Registry;
import com.zbz.rpc.registry.RegistryFactory;
import com.zbz.rpc.registry.impl.LocalRegistry;
import com.zbz.rpc.server.HttpServer;
import com.zbz.rpc.server.http.VertxHttpServer;
import com.zbz.rpc.server.tcp.VertxTcpServer;

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
        RpcApplication.init();
        String serviceName = UserService.class.getName();
        //提供服务
        LocalRegistry.register(serviceName, UserServiceImpl.class);
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
            e.printStackTrace();
        }
        // 启动 web 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
