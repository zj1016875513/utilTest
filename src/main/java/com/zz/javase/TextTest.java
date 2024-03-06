package com.zz.javase;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class TextTest {
    @Test
    public void enCodeTest(){
        String str = "测试字符串123";
        //转义为Unicode编码
        String encode1 = StringEscapeUtils.escapeJava(str);
        System.out.println(encode1);
        //反转义Unicode编码
        String decode = StringEscapeUtils.unescapeJava(encode1);
        System.out.println(decode);

        String md5 = DigestUtils.md5Hex(str);
        System.out.println(md5);
        System.out.println(md5.length());
    }
}
