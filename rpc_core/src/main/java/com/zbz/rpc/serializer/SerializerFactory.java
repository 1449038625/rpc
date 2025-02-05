package com.zbz.rpc.serializer;

import com.zbz.rpc.serializer.impl.JdkSerializer;
import com.zbz.rpc.spi.SpiLoader;

/**
 * Classname: SerializerFactory
 * Package: com.zbz.rpc.serializer
 * Decription:序列化器工厂
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/5 - 0:34
 * @Version: v1.0
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }
    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();
    /**
     * 根据key获取序列化器,获取的是一个实例
     * @param key
     * @return
     */
    public static Serializer getInstance(String key){
        return SpiLoader.getInstance(Serializer.class,key);
    }
}
