package com.zbz.rpc.fault.tolerant.impl;

import com.zbz.rpc.fault.tolerant.TolerantStrategy;
import com.zbz.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Classname: FailSafeTolerantStrategy
 * Package: com.zbz.rpc.fault.tolerant.impl
 * Decription:静默处理异常
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 20:19
 * @Version: v1.0
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy {
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常",e);
        return new RpcResponse();
    }
}
