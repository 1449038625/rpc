package com.zbz.rpc.enums;

import lombok.Getter;

/**
 * Classname: ProtocolMessageStatusEnum
 * Package: com.zbz.rpc.constant
 * Decription: 自定义协议的枚举类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 10:48
 * @Version: v1.0
 */
@Getter
public enum ProtocolMessageStatusEnum {
    OK("ok",20),
    BAD_REQUEST("badRequest", 40),
    BAD_RESPONSE("badResponse", 50);
    private final String text;
    private final int value;
    ProtocolMessageStatusEnum(String text,int value) {
        this.value = value;
        this.text = text;
    }
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum statusEnum : ProtocolMessageStatusEnum.values()) {
            if (statusEnum.value == value) {
                return statusEnum;
            }
        }
        return null;
    }

}
