package com.zbz.example.consumer;

import com.zbz.example.common.model.User;
import com.zbz.example.common.service.UserService;
import com.zbz.rpc.RpcApplication;
import com.zbz.rpc.config.RpcConfig;
import com.zbz.rpc.proxy.ServiceProxyFactory;
import com.zbz.rpc.utils.ConfigUtils;

/**
 * Classname: EasyConsumerExample
 * Package: com.zbz.example.consumer
 * Decription:消费者启动类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 11:10
 * @Version: v1.0
 */
public class ConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("张三");
        User newUser=userService.getUser(user);
        if(newUser==null){
            System.out.println("user==null");
        }else {
            System.out.println(newUser.getName());
        }
    }
}
