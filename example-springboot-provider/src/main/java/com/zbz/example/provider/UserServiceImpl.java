package com.zbz.example.provider;

import com.zbz.example.common.model.User;
import com.zbz.example.common.service.UserService;

/**
 * Classname: UserServiceImpl
 * Package: com.zbz.example.provider
 * Decription:UserService的实现类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 10:28
 * @Version: v1.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名"+user.getName());
        return user;
    }
}
