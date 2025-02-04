package com.zbz.rpc.serializer;

import java.io.IOException;

/**
 * Classname: Serializer
 * Package: com.zbz.rpc.serializer
 * Decription: 序列化接口
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 12:30
 * @Version: v1.0
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}

