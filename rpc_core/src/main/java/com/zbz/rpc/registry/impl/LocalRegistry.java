package com.zbz.rpc.registry.impl;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Classname: LocalRegistry
 * Package: com.zbz.rpc.register
 * Decription:本地服务注册器
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 12:03
 * @Version: v1.0
 */
public class LocalRegistry {
    /**
     * 服务注册表
     */
    private static final ConcurrentHashMap<String, Class<?>> serviceRegistry = new ConcurrentHashMap<>();

    /**
     * 注册服务
     *
     * @param serviceName 服务名称
     * @param implClass   实现类
     */
    public static void register(String serviceName, Class<?> implClass) {
        serviceRegistry.put(serviceName, implClass);
    }

    /**
     * 获取服务
     *
     * @param serviceName 服务名称
     * @return 实现类
     */
    public static Class<?> get(String serviceName) {
        Class<?> aClass = serviceRegistry.get(serviceName);
        if (aClass == null) {
            throw new RuntimeException("找不到服务：" + serviceName);
        }
        return aClass;
    }

    /**
     * 移除服务
     *
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        serviceRegistry.remove(serviceName);
    }


}
