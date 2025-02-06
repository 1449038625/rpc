package com.zbz.rpc.config;

import com.zbz.rpc.constant.LoadBalancerKeys;
import com.zbz.rpc.constant.RetryStrategyKeys;
import com.zbz.rpc.constant.SerializerKeys;
import com.zbz.rpc.constant.TolerantStrategyKeys;
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
    /**
     * 是否开启mock
     */
    private boolean mock=false;
    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;
    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();
    /**
     * 负载均衡器
     */
    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;
    /**
     * 重试机制
     */
    private String retryStrategy = RetryStrategyKeys.FIXED_INTERVAL;
    /**
     * 容错机制
     */
    private String tolerantStrategy = TolerantStrategyKeys.FAIL_SAFE;
}
