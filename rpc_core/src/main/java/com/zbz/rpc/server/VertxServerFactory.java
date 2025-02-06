package com.zbz.rpc.server;

import com.zbz.rpc.spi.SpiLoader;

/**
 * Classname: vertxServerFactory
 * Package: com.zbz.rpc.server
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 21:52
 * @Version: v1.0
 */
public class VertxServerFactory {
    static {
        SpiLoader.load(VertxServer.class);
    }
    public static VertxServer getInstance(String key)
    {
        return SpiLoader.getInstance(VertxServer.class,key);
    }
}
