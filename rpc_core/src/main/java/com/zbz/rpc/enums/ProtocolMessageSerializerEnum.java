package com.zbz.rpc.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classname: ProtocolMessageSerializerEnum
 * Package: com.zbz.rpc.enums
 * Decription:协议消息的序列化器枚举
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 10:57
 * @Version: v1.0
 */
@Getter
public enum ProtocolMessageSerializerEnum {
    JDK(0, "jdk"),
    JSON(1, "json"),
    KRYO(2, "kryo"),
    HESSIAN(3, "hessian");
    private final int key;
    private final String value;
    ProtocolMessageSerializerEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
    /**
     * 获取值列表
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item->item.value).collect(Collectors.toList());
    }
    /**
     * 根据key获取枚举
     * @param key
     * @return
     */
    public static ProtocolMessageSerializerEnum getEnumByKey(int key) {
        for (ProtocolMessageSerializerEnum item : values()) {
            if (item.key == key) {
                return item;
            }
        }
        return null;
    }
    /**
     * 根据value获取枚举
     * @param value
     * @return
     */
    public static ProtocolMessageSerializerEnum getEnumByValue(String value) {
        for (ProtocolMessageSerializerEnum item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }
}
