package com.zbz.rpc.utils;

import com.zbz.rpc.config.RpcConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classname: ConfigUtilsTest
 * Package: com.zbz.rpc.utils
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 20:15
 * @Version: v1.0
 */
class ConfigUtilsTest {

    @Test
    void loadConfig() {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class,null);
        System.out.println("rpcConfig = " + rpcConfig);
    }
}