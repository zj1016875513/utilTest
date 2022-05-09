package com.zz.annotation.annotation1;

import org.junit.Test;

public class UserTest {
    @Test
    public void t1(){
        System.out.println(saveUser(new User("MRyan", "123456")));
        System.out.println(saveUser(new User("", "123456")));
        System.out.println(saveUser(new User("MRyan", "1234566666666666")));
        System.out.println(saveUser(new User("MRyan", "12")));
    }

    @Test
    public void t2(){
        System.out.println(UserCheck.check(new User("MRyan", "123456")));
        System.out.println(UserCheck.check(new User("", "123456")));
        System.out.println(UserCheck.check(new User("MRyan", "1234566666666666")));
        System.out.println(UserCheck.check(new User("MRyan", "12")));
    }

    /**
     * 模拟接收（username）用户名 （passwoord）密码  验证用户信息时候符合要求
     */
    public static boolean saveUser(User user) {
        //User为空返回false
        if (user == null) {
            System.out.println("失败：不可为空");
            return false;
        }
        String username = user.getUsername();
        String password = user.getPassword();
        if (username == null || "".equals(username)) {
            System.out.println("｛用户名可空校验｝失败：不可为空");
            return false;
        } else {
            System.out.println("｛用户名可空校验｝通过！！！");
            if (username.length() > 6) {
                System.out.println("｛用户名长度校验｝失败：长度不能超过6");
                return false;
            } else {
                System.out.println("｛用户名长度校验｝通过！！！");
            }
        }

        if (password == null || "".equals(password)) {
            System.out.println("｛密码可空校验｝失败：不可为空");
            return false;
        } else {
            System.out.println("｛密码可空校验｝通过");
            if (password.length() < 5 || password.length() > 13) {
                System.out.println("｛密码长度校验｝失败：长度不能低于5，不能超过13");
                return false;
            } else {
                System.out.println("｛用户名长度校验｝通过！！！");
            }
        }
        return true;
    }
}
