package com.zbz.rpc.registry;

import com.zbz.rpc.registry.impl.EtcdRegistry;
import com.zbz.rpc.spi.SpiLoader;

/**
 * Classname: RegistryFactory
 * Package: com.zbz.rpc.register
 * Decription:工厂类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/5 - 15:37
 * @Version: v1.0
 */
public class RegistryFactory {
    static {
        SpiLoader.load(Registry.class);
    }
    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();
    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
