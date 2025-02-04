package com.zbz.rpc.config;

import lombok.Data;

/**
 * Classname: RpcConfig
 * Package: com.zbz.rpc.config
 * Decription:rpc的一些配置类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 19:55
 * @Version: v1.0
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "zbz-rpc";
    /**
     * 版本
     */
    private String version = "1.0";
    /**
     * 服务端网址
     */
    private String serverHost="localhost";
    /**
     * 服务端端口
     */
    private Integer serverPort=8080;
}
