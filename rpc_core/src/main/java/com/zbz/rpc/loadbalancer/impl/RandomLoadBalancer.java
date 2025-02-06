package com.zbz.rpc.loadbalancer.impl;

import com.zbz.rpc.loadbalancer.LoadBalancer;
import com.zbz.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Classname: RandomLoadBalancer
 * Package: com.zbz.rpc.loadbalancer.impl
 * Decription:随机负载均衡算法
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 17:07
 * @Version: v1.0
 */
public class RandomLoadBalancer implements LoadBalancer {
    private final Random random = new Random();
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        int size=serviceMetaInfoList.size();
        if(size==0){
            return null;
        }
        if(size==1){
            return serviceMetaInfoList.get(0);
        }
        return serviceMetaInfoList.get(random.nextInt(size));
    }
}
