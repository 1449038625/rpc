package com.zbz.rpc.config;

import lombok.Data;

/**
 * Classname: RegistryConfig
 * Package: com.zbz.rpc.config
 * Decription:注册中心配置类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/5 - 15:08
 * @Version: v1.0
 */
@Data
public class RegistryConfig {
    /**
     * 注册中心类别
     */
    private String registry = "etcd";
    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2380";
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 100000L;
}
