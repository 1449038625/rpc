package com.zbz.rpc.fault.retry;

import com.zbz.rpc.fault.retry.impl.FixedIntervalRetryStrategy;
import com.zbz.rpc.fault.retry.impl.NoRetryStrategy;
import com.zbz.rpc.model.RpcResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classname: RetryStrategyTest
 * Package: com.zbz.rpc.fault.retry
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 19:48
 * @Version: v1.0
 */
class RetryStrategyTest {
    RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();
    @Test
    public void doRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}