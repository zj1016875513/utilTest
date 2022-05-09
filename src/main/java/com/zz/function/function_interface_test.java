package com.zz.function;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 *函数式编程的几大接口 demo
 */

public class function_interface_test {
    @Test
    public void Runnable(){

    }

    @Test
    public void Function(){
        Function<String,String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };
        System.out.println(function.apply("Sky"));

        //lambda 表达式写法：
        function = ((str)->{ return str;});
        System.out.println(function.apply("Song"));
    }

    @Test
    public void Predicate(){
        Predicate<Integer> predicate =new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return 0 != integer;
            }
        };
        System.out.println(predicate.test(0));

        //lambda 表达式写法：
        predicate = (integer -> {return 0 != integer;});
        System.out.println(predicate.test(1));
    }

    @Test
    public void Consumer(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("消费："+s);
            }
        };
        consumer.accept("cake");

        //lambda 表达式写法：
        consumer = (str)->{ System.out.println("消费："+str); };
        consumer.accept("money");
    }

    @Test
    public void Supplier(){
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "提供：Sky";
            }
        };
        System.out.println(supplier.get());

        //lambda 表达式写法：
        supplier = ()->{return "提供：Song";};
        System.out.println(supplier.get());
    }

}
