package com.zbz.rpc.protocol;

import cn.hutool.core.util.IdUtil;
import com.zbz.rpc.constant.ProtocolConstant;
import com.zbz.rpc.constant.RpcConstant;
import com.zbz.rpc.enums.ProtocolMessageSerializerEnum;
import com.zbz.rpc.enums.ProtocolMessageStatusEnum;
import com.zbz.rpc.enums.ProtocolMessageTypeEnum;
import com.zbz.rpc.model.RpcRequest;
import io.vertx.core.buffer.Buffer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classname: ProtocolMessageTest
 * Package: com.zbz.rpc.protocol
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 13:40
 * @Version: v1.0
 */
class ProtocolMessageTest {
    @Test
    public void testEncodeAndDecode() throws Exception {
        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) ProtocolMessageSerializerEnum.JDK.getKey());
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) ProtocolMessageStatusEnum.OK.getValue());
        header.setRequestId(IdUtil.getSnowflakeNextId());
        header.setBodyLength(0);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName("myService");
        rpcRequest.setMethodName("myMethod");
        rpcRequest.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        rpcRequest.setParameterTypes(new Class[]{String.class, int.class});
        rpcRequest.setArgs(new Object[]{"hello", 1});
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);
        Buffer buffer = ProtocolMessageEncoder.encode(protocolMessage);
        ProtocolMessage<?> decode = ProtocolMessageDecoder.decode(buffer);
        Assert.assertNotNull(decode);
    }

}