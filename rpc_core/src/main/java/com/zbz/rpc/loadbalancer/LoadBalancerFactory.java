package com.zbz.rpc.loadbalancer;

import com.zbz.rpc.loadbalancer.impl.RandomLoadBalancer;
import com.zbz.rpc.spi.SpiLoader;

/**
 * Classname: LoadBalancerFactory
 * Package: com.zbz.rpc.loadbalancer
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 18:36
 * @Version: v1.0
 */
public class LoadBalancerFactory {
    static {
        SpiLoader.load(LoadBalancer.class);
    }
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RandomLoadBalancer();

    /**
     * 获取负载均衡器
     * @param key
     * @return
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }
}
