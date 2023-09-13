package com.example.demo.auth.model;

import lombok.Data;

/**
 * 用户登录对象
 *
 *  
 */
@Data
public class LoginUser {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    private String code;
}
