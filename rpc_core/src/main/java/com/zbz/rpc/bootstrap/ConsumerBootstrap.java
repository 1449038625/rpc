package com.zbz.rpc.bootstrap;

import com.zbz.rpc.RpcApplication;

/**
 * Classname: ConsumerBootstrap
 * Package: com.zbz.rpc.bootstrap
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 21:23
 * @Version: v1.0
 */
public class ConsumerBootstrap {
    /**
     * 初始化
     */
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
