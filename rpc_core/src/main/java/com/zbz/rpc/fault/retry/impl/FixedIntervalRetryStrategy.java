package com.zbz.rpc.fault.retry.impl;

import com.github.rholder.retry.*;
import com.zbz.rpc.fault.retry.RetryStrategy;
import com.zbz.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Classname: FixedIntervalRetryStrategy
 * Package: com.zbz.rpc.fault.retry.impl
 * Decription:固定重试间隔策略
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 19:35
 * @Version: v1.0
 */
@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy {

    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfExceptionOfType(Exception.class)
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数{}", attempt.getAttemptNumber());
                    }
                })
                .build();
        return retryer.call(callable);
    }
}
