package com.zbz.rpc.constant;

/**
 * Classname: RpcConstant
 * Package: com.zbz.rpc.constant
 * Decription:默认配置文件的一些配置信息
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 20:33
 * @Version: v1.0
 */
public interface RpcConstant {
    /**
     * 默认配置文件加载前缀
     */
    String DEFAULT_CONFIG_PREFIX = "rpc";
    /**
     * 默认服务版本
     */
    String DEFAULT_SERVICE_VERSION = "1.0";
    /**
     * 默认SPI扩展系统的文件地址
     */
    String RPC_SYSTEM_SPI_DIR = "META-INF/rpc/system/";
    /**
     * 默认SPI扩展自定义的文件地址
     */
    String RPC_CUSTOM_SPI_DIR="META-INF/rpc/custom/";
    /**
     * etcd的根路径
     */
    String ETCD_ROOT_PATH = "/rpc/";
}
