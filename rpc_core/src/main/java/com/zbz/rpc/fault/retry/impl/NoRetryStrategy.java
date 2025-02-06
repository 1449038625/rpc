package com.zbz.rpc.fault.retry.impl;

import com.zbz.rpc.fault.retry.RetryStrategy;
import com.zbz.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * Classname: NoRetryStrategy
 * Package: com.zbz.rpc.fault.retry.impl
 * Decription:不重试策略
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 19:34
 * @Version: v1.0
 */
@Slf4j
public class NoRetryStrategy implements RetryStrategy {
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
