package com.zbz.rpc.constant;

/**
 * Classname: LoadBalancerKeys
 * Package: com.zbz.rpc.constant
 * Decription:负载均衡器常量包
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 18:35
 * @Version: v1.0
 */
public interface LoadBalancerKeys {
    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";
    String RANDOM = "random";
    String CONSISTENT_HASH = "consistentHash";
}
