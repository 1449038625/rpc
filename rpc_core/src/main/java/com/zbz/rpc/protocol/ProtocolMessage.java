package com.zbz.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classname: ProtocolMessage
 * Package: com.zbz.rpc.protocol
 * Decription:协议封装类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 10:43
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProtocolMessage<T> {
    /**
     * 消息头
     */
    private Header header;
    /**
     * 消息体
     */
    private T body;

    @Data
    public static class Header {
        /**
         * 魔数
         */
        private byte magic;
        /**
         * 版本号
         */
        private byte version;
        /**
         * 序列化方式(类似于content/type)
         */
        private byte serializer;
        /**
         * 消息类型(请求/响应)
         */
        private byte type;
        /**
         * 状态
         */
        private byte status;
        /**
         * 请求id
         */
        private long requestId;
        /**
         * 消息体长度
         */
        private int bodyLength;
    }
}
