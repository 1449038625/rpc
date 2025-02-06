package com.zbz.rpc.enums;

import lombok.Getter;

/**
 * Classname: ProtocolMessageTypeEnum
 * Package: com.zbz.rpc.enums
 * Decription:协议消息类型枚举
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 10:55
 * @Version: v1.0
 */
@Getter
public enum ProtocolMessageTypeEnum {
    REQUEST(0),
    RESPONSE(1),
    HEART_BEAT(2),
    OTHERS(3);
    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    /**
     * 根据 key 获取枚举
     *
     * @param key
     * @return
     */
    public static ProtocolMessageTypeEnum getEnumByKey(int key) {
        for (ProtocolMessageTypeEnum anEnum : ProtocolMessageTypeEnum.values()) {
            if (anEnum.key == key) {
                return anEnum;
            }
        }
        return null;
    }
}
