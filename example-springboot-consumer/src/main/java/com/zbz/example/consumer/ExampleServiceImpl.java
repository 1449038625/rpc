package com.zbz.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zbz.example.common.model.User;
import com.zbz.example.common.service.UserService;
import com.zbz.rpc.model.RpcRequest;
import com.zbz.rpc.model.RpcResponse;
import com.zbz.rpc.serializer.impl.JdkSerializer;
import com.zbz.rpc.springboot.starter.annotation.RpcReference;

/**
 * Classname: ExampleServiceImpl
 * Package: com.zbz.example.consumer
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 15:55
 * @Version: v1.0
 */
public class ExampleServiceImpl {
    @RpcReference
    private UserService userService;
    public void test() {
        User user = new User();
        user.setName("yupi");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}
