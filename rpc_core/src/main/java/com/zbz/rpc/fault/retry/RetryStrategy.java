package com.zbz.rpc.fault.retry;

import com.zbz.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * Classname: RetryStrategy
 * Package: com.zbz.rpc.fault.retry
 * Decription:重试策略
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 19:32
 * @Version: v1.0
 */
public interface RetryStrategy {
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
