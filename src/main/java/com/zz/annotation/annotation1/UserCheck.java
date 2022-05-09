package com.zz.annotation.annotation1;

import java.lang.reflect.Field;

public class UserCheck {

    public static boolean check(User user) {
        if (user == null) {
            System.out.println("失败：不可为空");
            return false;
        }
        // 获取User类的所有属性（如果使用getFields，就无法获取到private的属性）
        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //将反射字段上的注解值(如果某几个注解属性没有，则值为注解默认值) 生成一个对象userValidate
            UserValidate userValidate = declaredField.getAnnotation(UserValidate.class);
            if (declaredField.getName().equals("username")) {
                if (user.getUsername() == null || "".equals(user.getUsername())) {
                    if (userValidate.canNotNull()) {
                        System.out.println("｛用户名可空校验｝失败：不可为空");
                        return false;
                    } else {
                        System.out.println("｛用户名可空校验｝通过！！！");
                    }
                }
                if (user.getUsername().length() > userValidate.username_maxLength()) {
                    System.out.println("｛用户名长度校验｝失败");
                    return false;
                } else {
                    System.out.println("｛用户名长度校验｝通过！！！");
                }
            }

            if (declaredField.getName().equals("password")) {
                if (user.getPassword() == null || "".equals(user.getPassword())) {
                    if (userValidate.canNotNull()) {
                        System.out.println("｛密码可空校验｝失败：不可为空");
                        return false;
                    } else {
                        System.out.println("｛密码可空校验｝通过！！！");
                    }
                }
                if (user.getPassword().length() < userValidate.password_minLength() || user.getPassword().length() > userValidate.username_maxLength()) {
                    System.out.println("｛密码长度校验｝失败");
                    return false;
                } else {
                    System.out.println("｛密码长度校验｝通过！！！");
                }
            }
        }
        return true;
    }
}
