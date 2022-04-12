package com.zz.annotation1;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface UserValidate {
    /**
     * 用户名长度最大值
     */
    public int username_maxLength() default 10;


    /**
     * 密码长度最小值
     */
    public int password_minLength() default 2;

    /**
     * 密码长度最大值
     */
    public int password_maxLength() default 20;

    /**
     * 是否支持为空
     */
    public boolean canNotNull() default false;
}