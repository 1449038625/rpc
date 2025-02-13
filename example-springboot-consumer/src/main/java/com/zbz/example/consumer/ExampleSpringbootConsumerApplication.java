package com.zbz.example.consumer;

import com.zbz.example.common.model.User;
import com.zbz.example.common.service.UserService;
import com.zbz.rpc.proxy.ServiceProxyFactory;
import com.zbz.rpc.springboot.starter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classname: ExampleSpringbootConsumerApplication
 * Package: com.zbz.example.consumer
 * Decription:消费者启动类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 11:10
 * @Version: v1.0
 */
@SpringBootApplication
@EnableRpc(needServer = false)
public class ExampleSpringbootConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringbootConsumerApplication.class, args);
    }
}
