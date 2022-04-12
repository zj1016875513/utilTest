package com.zz.annotation1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

/*public class User {

    *//**
     * 用户名 不能为空 长度不能大于6
     *//*
    private String username;

    *//**
     * 密码 不能为空 长度不能低于5 大于13
     *//*
    private String password;

}*/

public class User {

    /**
     * 用户名 不能为空 长度不能大于6
     */
    @UserValidate(canNotNull = true, username_maxLength = 6)
    private String username;

    /**
     * 密码 不能为空 长度不能低于5 大于13
     */
    @UserValidate(canNotNull = true, password_minLength = 5, password_maxLength = 13)
    private String password;

}
