package com.zbz.example.springboot.provider;

import com.zbz.example.common.model.User;
import com.zbz.example.common.service.UserService;
import com.zbz.rpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
