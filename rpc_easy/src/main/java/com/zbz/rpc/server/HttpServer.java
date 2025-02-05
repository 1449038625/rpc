package com.zbz.rpc.server;

/**
 * Classname: HttpServer
 * Package: com.zbz.rpc.server
 * Decription:服务器接口
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 11:17
 * @Version: v1.0
 */
public interface HttpServer {
    /**
     * 启动服务
     * @param port
     */
    void doStart(int port);
}
