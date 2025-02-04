package com.zbz.rpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * Classname: ConfigUtils
 * Package: com.zbz.rpc.utils
 * Decription:读取配置文件并返回配置对象
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 20:00
 * @Version: v1.0
 */
public class ConfigUtils {
    /**
     * 根据配置文件加载配置对象
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass,String prefix) {
        return loadConfig(tClass,prefix,"");
    }

    /**
     * 根据配置文件加载配置对象,支持区分环境
     * @param tClass
     * @param prefix
     * @param env
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass,String prefix,String env) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if(StrUtil.isNotBlank(env)){
            configFileBuilder.append("-").append(env);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass,prefix);
    }
}
