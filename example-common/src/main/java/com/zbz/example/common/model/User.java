package com.zbz.example.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Classname: User
 * Package: com.zbz.example.common.model
 * Decription: 用户类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 10:16
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User(String name) {
        this.name = name;
    }
}
