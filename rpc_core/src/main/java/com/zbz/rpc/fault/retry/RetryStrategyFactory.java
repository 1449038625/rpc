package com.zbz.rpc.fault.retry;

import com.zbz.rpc.spi.SpiLoader;

/**
 * Classname: RetryStrategyFactory
 * Package: com.zbz.rpc.fault.retry
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 19:50
 * @Version: v1.0
 */
public class RetryStrategyFactory {
    static {
        SpiLoader.load(RetryStrategy.class);
    }

    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }
}
