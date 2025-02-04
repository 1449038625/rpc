package com.zbz.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * Classname: ServiceProxyFactory
 * Package: com.zbz.rpc.proxy
 * Decription:代理工厂
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 17:01
 * @Version: v1.0
 */
public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }
}
