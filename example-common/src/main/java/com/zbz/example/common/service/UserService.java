package com.zbz.example.common.service;

import com.zbz.example.common.model.User;

/**
 * Classname: UserService
 * Package: com.zbz.example.common.service
 * Decription:提供获取用户的方法
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 10:19
 * @Version: v1.0
 */
public interface UserService {
    User getUser(User user);

    default short getNumber(){
        return 1;
    }
}
