package com.zbz.rpc;

import com.zbz.rpc.config.RpcConfig;
import com.zbz.rpc.constant.RpcConstant;
import com.zbz.rpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Classname: RpcApplication
 * Package: com.zbz.rpc
 * Decription:Rpc启动类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 20:38
 * @Version: v1.0
 */
@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;
    /**
     * 框架初始化，可以传入自定义配置
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig=newRpcConfig;
        log.info("rpc init,config = {}",newRpcConfig.toString());
    }
    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        }catch (Exception e){
            newRpcConfig=new RpcConfig();
        }
        init(newRpcConfig);
    }
    /**
     * 获取配置
     * @return RpcConfig
     */
    public static RpcConfig getRpcConfig() {
        if(rpcConfig==null){
            synchronized (RpcApplication.class){
                if(rpcConfig==null){
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
