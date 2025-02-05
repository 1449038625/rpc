package com.zbz.rpc.cache;

import com.zbz.rpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * Classname: RegistryServiceCache
 * Package: com.zbz.rpc.cache
 * Decription:注册中心服务缓存
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/5 - 22:58
 * @Version: v1.0
 */
public class RegistryServiceCache {
    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;
    /**
     * 写入缓存
     * @param newServiceCache
     */
    public void writeCache(List<ServiceMetaInfo> newServiceCache)
    {
        this.serviceCache = newServiceCache;
    }
    /**
     * 获取缓存
     * @return
     */
    public List<ServiceMetaInfo> readCache()
    {
        return this.serviceCache;
    }
    /**
     * 清空缓存
     */
    public void clearCache()
    {
        this.serviceCache = null;
    }
}
