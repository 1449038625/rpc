package com.zbz.rpc.server;

import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.model.RpcRequest;
import com.zbz.rpc.model.RpcResponse;
import com.zbz.rpc.registry.impl.LocalRegistry;
import com.zbz.rpc.serializer.Serializer;
import com.zbz.rpc.serializer.SerializerFactory;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Classname: HttpServerHandler
 * Package: com.zbz.rpc.server
 * Decription:请求处理器
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 14:55
 * @Version: v1.0
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        System.out.println("Received request: " + httpServerRequest.method()+" "+httpServerRequest.uri());
        httpServerRequest.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest=null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            RpcResponse rpcResponse = new RpcResponse();
            if(rpcRequest==null){
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(httpServerRequest, rpcResponse, serializer);
                return;
            }
            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("success");
            }catch (Exception e){
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(httpServerRequest, rpcResponse, serializer);
        });
    }

    void doResponse(HttpServerRequest httpServerRequest, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = httpServerRequest.response().putHeader("content-type", "application/json");
        try {
            byte[] bytes = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
