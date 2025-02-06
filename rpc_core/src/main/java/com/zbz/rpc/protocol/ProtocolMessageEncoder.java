package com.zbz.rpc.protocol;


import com.zbz.rpc.enums.ProtocolMessageSerializerEnum;
import com.zbz.rpc.serializer.Serializer;
import com.zbz.rpc.serializer.SerializerFactory;
import io.vertx.core.buffer.Buffer;

/**
 * Classname: ProtocolMessageEncoder
 * Package: com.zbz.rpc.protocol
 * Decription:协议编码器
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 12:02
 * @Version: v1.0
 */
public class ProtocolMessageEncoder {
    public static Buffer encode(ProtocolMessage<?> protocolMessage) throws Exception{
        if(protocolMessage == null||protocolMessage.getHeader()==null){
            return Buffer.buffer();
        }
        ProtocolMessage.Header header = protocolMessage.getHeader();
        Buffer buffer = Buffer.buffer();
        buffer.appendByte(header.getMagic())
                .appendByte(header.getVersion())
                .appendByte(header.getSerializer())
                .appendByte(header.getType())
                .appendByte(header.getStatus());
        buffer.appendLong(header.getRequestId());
        ProtocolMessageSerializerEnum serializerEnum = ProtocolMessageSerializerEnum.getEnumByKey(header.getSerializer());
        if(serializerEnum == null){
            throw new RuntimeException("serializer not found");
        }
        Serializer serializer = SerializerFactory.getInstance(serializerEnum.getValue());
        byte[] bodyBytes = serializer.serialize(protocolMessage.getBody());
        buffer.appendInt(bodyBytes.length);
        buffer.appendBytes(bodyBytes);
        return buffer;
    }
}
