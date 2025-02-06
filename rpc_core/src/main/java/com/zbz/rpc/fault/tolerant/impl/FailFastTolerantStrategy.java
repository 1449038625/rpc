package com.zbz.rpc.fault.tolerant.impl;

import com.zbz.rpc.fault.tolerant.TolerantStrategy;
import com.zbz.rpc.model.RpcResponse;

import java.util.Map;

/**
 * Classname: FailFastTolerantStrategy
 * Package: com.zbz.rpc.fault.tolerant.impl
 * Decription:快速失败容错策略
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 20:17
 * @Version: v1.0
 */
public class FailFastTolerantStrategy implements TolerantStrategy {
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("fail fast",e);
    }
}
