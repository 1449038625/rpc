package com.zbz.rpc.loadbalancer.impl;

import com.zbz.rpc.loadbalancer.LoadBalancer;
import com.zbz.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classname: RoundRobinLoadBalancer
 * Package: com.zbz.rpc.loadbalancer.impl
 * Decription:轮询负载均衡算法
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 17:03
 * @Version: v1.0
 */
public class RoundRobinLoadBalancer implements LoadBalancer {
    /**
     * 当前轮询的下标
     */
    private final AtomicInteger currentIndex=new AtomicInteger(0);

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty()){
            return null;
        }
        int size = serviceMetaInfoList.size();
        if(size==1){
            return serviceMetaInfoList.get(0);
        }
        int i = currentIndex.getAndIncrement() % size;
        return serviceMetaInfoList.get(i);
    }
}
