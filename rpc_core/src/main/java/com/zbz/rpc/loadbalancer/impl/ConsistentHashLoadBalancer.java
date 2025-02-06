package com.zbz.rpc.loadbalancer.impl;

import com.zbz.rpc.loadbalancer.LoadBalancer;
import com.zbz.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classname: ConsistentHashLoadBalancer
 * Package: com.zbz.rpc.loadbalancer.impl
 * Decription:一致性hash算法
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 17:09
 * @Version: v1.0
 */
public class ConsistentHashLoadBalancer implements LoadBalancer {
    /**
     * 一致性hash环
     */
    private final TreeMap<Integer, ServiceMetaInfo> virtualNodes=new TreeMap<>();
    /**
     * 虚拟节点个数
     */
    private static final int VIRTUAL_NODE_NUM=10;
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()){
            return null;
        }
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }
        int hash=getHash(requestParams);
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if(entry==null){
            entry=virtualNodes.firstEntry();
        }
        return entry.getValue();
    }


    private int getHash(Object key){
        return key.hashCode();
    }
}
