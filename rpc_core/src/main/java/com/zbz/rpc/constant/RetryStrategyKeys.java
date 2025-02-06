package com.zbz.rpc.constant;

/**
 * Classname: RetryStrategyKeys
 * Package: com.zbz.rpc.constant
 * Decription:重试策略keys接口
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 19:50
 * @Version: v1.0
 */
public interface RetryStrategyKeys {
    /**
     * 不重试
     */
    String NO = "no";
    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";
}
