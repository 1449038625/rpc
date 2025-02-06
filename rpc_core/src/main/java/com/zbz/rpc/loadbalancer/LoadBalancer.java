package com.zbz.rpc.loadbalancer;

import com.zbz.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * Classname: LoadBalancer
 * Package: com.zbz.rpc.loadbalancer
 * Decription:负载均衡算法接口
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 17:01
 * @Version: v1.0
 */
public interface LoadBalancer {
    /**
     * 选择服务调用
     *
     * @param requestParams       请求参数
     * @param serviceMetaInfoList 可用服务列表
     * @return
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);

}
