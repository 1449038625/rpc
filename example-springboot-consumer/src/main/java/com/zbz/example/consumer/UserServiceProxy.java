package com.zbz.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zbz.example.common.model.User;
import com.zbz.example.common.service.UserService;
import com.zbz.rpc.model.RpcRequest;
import com.zbz.rpc.model.RpcResponse;
import com.zbz.rpc.serializer.impl.JdkSerializer;

/**
 * Classname: UserServiceProxy
 * Package: com.zbz.example.consumer
 * Decription:代理对象
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 15:55
 * @Version: v1.0
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        JdkSerializer serializer = new JdkSerializer();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            byte[] bytes = serializer.serialize(rpcRequest);
            try (HttpResponse httpResponse =HttpRequest.post("http://localhost:8080")
                         .body(bytes)
                         .execute()){
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return (User) rpcResponse.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
