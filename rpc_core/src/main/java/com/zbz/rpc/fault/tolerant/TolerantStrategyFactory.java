package com.zbz.rpc.fault.tolerant;

import com.zbz.rpc.spi.SpiLoader;

/**
 * Classname: TolerantStrategyFactory
 * Package: com.zbz.rpc.fault.tolerant
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 20:21
 * @Version: v1.0
 */
public class TolerantStrategyFactory {
    static {
        SpiLoader.load(TolerantStrategy.class);
    }
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
