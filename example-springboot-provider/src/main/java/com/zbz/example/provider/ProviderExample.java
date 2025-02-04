package com.zbz.example.provider;

import com.zbz.example.common.service.UserService;
import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.register.LocalRegistry;
import com.zbz.rpc.server.HttpServer;
import com.zbz.rpc.server.VertxHttpServer;

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
        //提供服务
        // todo 注册服务可以采用注解方式实现
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
