package com.zbz.rpc.fault.tolerant;

import com.zbz.rpc.model.RpcResponse;

import java.util.Map;

/**
 * Classname: TolerantStrategy
 * Package: com.zbz.rpc.fault.tolerant
 * Decription:容错接口
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 20:16
 * @Version: v1.0
 */
public interface TolerantStrategy {
    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e 异常
     * @return
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
