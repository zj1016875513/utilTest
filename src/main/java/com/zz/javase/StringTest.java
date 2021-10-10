package com.zz.javase;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

public class StringTest {
    public static void main(String[] args) {

    }

    @Test
    public void stringUtilsTest() {
        String str = "hello world";
        //将第一个字符大写
        String capitalize = StringUtils.capitalize(str);
        System.out.println(capitalize);//Hello world

        //两边空格补齐
        String center1 = StringUtils.center(str, 7);
        System.out.println(center1);// hello world
        String center2 = StringUtils.center(str, 15, "*");
        System.out.println(center2);//**hello world**

        //chomp剔除字符串最后一个是\n或\r\或\r\n
        String str1="hello word";
        String chomp = StringUtils.chomp(str1);
        System.out.println(chomp);//hello word
        //chop剔除字符串最后一个字符（如果最后一个是\n或\r\或\r\n也剔除掉）
        String chop = StringUtils.chop(str1);
        System.out.println(chop);//hello worl

        //是否包含任意字符
        boolean containsAny = StringUtils.containsAny(str, "W");//str字符串不包含字母W
        System.out.println(containsAny);

        //是否包含任意字符，忽略大小写
        containsAny = StringUtils.containsIgnoreCase(str, "W");//str字符串包含忽略大小写的字母W
        System.out.println(containsAny);

        //有意思
        int indexOfAnyBut1 = StringUtils.indexOfAnyBut(str, "hello");
        System.out.println(indexOfAnyBut1); //5 前5个字符串一样
        int indexOfAnyBut2 = StringUtils.indexOfAnyBut("hello world", "hello wArld"); //不知道为啥为-1
        System.out.println(indexOfAnyBut2); //-1

        System.out.println("-------");
        //两个字符串从第几位开始不同
        int indexOfDifference = StringUtils.indexOfDifference(str, str);
        System.out.println(indexOfDifference); //-1
        indexOfDifference = StringUtils.indexOfDifference(str, str+"zs");
        System.out.println(indexOfDifference);//11
        indexOfDifference = StringUtils.indexOfDifference(str, "he llo");
        System.out.println(indexOfDifference);//2
        int i = StringUtils.indexOfDifference("hello world", "hello wArld");
        System.out.println(i); //7

        //判断是否是正整数
        boolean isNumeric = StringUtils.isNumeric(str);
        boolean isNumericSpace = StringUtils.isNumericSpace(str);
        System.out.println(isNumeric);//false
        System.out.println(isNumericSpace);//false

        isNumeric = StringUtils.isNumeric("1234dd");
        isNumericSpace = StringUtils.isNumericSpace("1234dd");
        System.out.println("1234dd:"+isNumeric);//false
        System.out.println("1234dd:"+isNumericSpace);//false

        isNumeric = StringUtils.isNumeric("1234 ");
        isNumericSpace = StringUtils.isNumericSpace("1234 ");
        System.out.println("1234 :"+isNumeric);//false
        System.out.println("1234 :"+isNumericSpace);//true

        isNumeric = StringUtils.isNumeric("1.234");
        isNumericSpace = StringUtils.isNumericSpace("1.234");
        System.out.println("1.234:"+isNumeric);//false
        System.out.println("1.234:"+isNumericSpace);//false

        isNumeric = StringUtils.isNumeric("1234");
        isNumericSpace = StringUtils.isNumericSpace("1234");
        System.out.println("1234:" + isNumeric);//true
        System.out.println("1234:" + isNumericSpace);//true

        isNumeric = StringUtils.isNumeric("-1234");
        isNumericSpace = StringUtils.isNumericSpace("-1234");
        System.out.println("-1234:" + isNumeric);//false
        System.out.println("-1234:" + isNumericSpace);//false


        //将数组变为字符串，并添加字符进行分割
        Object[] array1 = new Object[] {"zs",18,"男",666,777};
        String join1 = StringUtils.join(array1);
        System.out.println(Arrays.toString(array1));//[zs, 18, 男, 666, 777]
        System.out.println(join1);//zs18男666777
        join1 = StringUtils.join(array1, "*");
        System.out.println(join1);//zs*18*男*666*777
        Object[] array2 = new Object[] {"zs",18,"男",666,777};
        String join2 = StringUtils.join(array2, ";", 2, 5);
        System.out.println(join2);//男;666;777

        String remove = StringUtils.remove(str, "hello");
        System.out.println(remove);// world
        String repeat = StringUtils.repeat(str, 3); //重复3次
        System.out.println(repeat);//hello worldhello worldhello world
        repeat = StringUtils.repeat(str, ",", 3);//重复3次 中间用 ， 分割
        System.out.println(repeat);//hello world,hello world,hello world

        System.out.println("--------");
        //分别替换
        String[] list =  new String[] {"hello", "girl"};
        String[] replacementList =  new String[] {"say", "woman"};
        String str2 = "hello world,hello boy,hello girl.";
        String replaceEach = StringUtils.replaceEach(str2, list, replacementList);//将str2中的 list  替换为 replacementlist
        System.out.println(replaceEach);//say world,say boy,say woman.

        //只替换第一个匹配的字符串
        String replaceOnce = StringUtils.replaceOnce("hello world,hello boy,hello girl.", "hello", "say");
        System.out.println(replaceOnce);//say world,hello boy,hello girl.

        //截取某字符后面的部分
        String substringAfterLast = StringUtils.substringAfterLast("hello world,hello boy,hello girl.", " ");//截取最后一个空格后的字符串
        System.out.println(substringAfterLast);//girl.

        String substringBeforeLast = StringUtils.substringBeforeLast("hello world,hello boy,hello girl.", " ");
        System.out.println(substringBeforeLast);//hello world,hello boy,hello
    }

}
