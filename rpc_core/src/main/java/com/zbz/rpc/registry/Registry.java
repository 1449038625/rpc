package com.zbz.rpc.registry;

import com.zbz.rpc.config.RegistryConfig;
import com.zbz.rpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * Classname: Registry
 * Package: com.zbz.rpc.register
 * Decription:注册中心接口
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/5 - 15:10
 * @Version: v1.0
 */
public interface Registry {
    /**
     * 初始化
     *
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     *
     * @param serviceMetaInfo
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     *
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);
    /**
     * 服务发现（获取某接口服务的所有可服务节点，消费端）
     *
     * @param serviceKey 服务键名
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);
    /**
     * 服务销毁
     */
    void destroy();
    /**
     * 心跳检测方法
     */
    void heartBeat();
    /**
     * 监听（消费端）
     *
     * @param serviceNodeKey
     */
    void watch(String serviceNodeKey);
}
