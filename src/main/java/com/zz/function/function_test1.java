package com.zz.function;

import org.junit.Test;

import java.util.function.Function;

public class function_test1 {

    @Test
    public void t1(){
        Function<Integer,Integer> f1 = arg -> arg*3;
        Function<Integer,Integer> f2 = arg -> arg*arg;
        int result1 = f1.apply(1); //result1: 3
        int result2 = f2.apply(1);//result2: 1
        System.out.println(result1);
        System.out.println(result2);
        System.out.println("------------");

        Function<Integer,Integer> f11 = f1.compose(f2);
        Function<Integer,Integer> f12 = f2.compose(f1);
        System.out.println(f11.apply(2)); //先执行f2再执行f1 (2^2)*3
        System.out.println(f12.apply(2)); //先执行f1再执行f2 (2*3)^2
        System.out.println("------------");

        Function<Integer, Function<Integer, Integer>> add = x -> (y -> (x + y));
        System.out.println(add.apply(3).apply(5));//result: 8
        System.out.println("------------");

        Function<Integer, Function<Integer, Function<Integer, Integer>>> axPlusb = a -> (x -> (b -> (a * x + b)));
        System.out.println(axPlusb.apply(2).apply(3).apply(4)); // 2*3+4 =6
        Function<Integer, Function<Integer, Function<Integer, Integer>>> axPlusb1 = a -> (x -> (b -> (a * b + x)));
        System.out.println(axPlusb1.apply(2).apply(3).apply(4)); // 2*4+3 =11
        System.out.println("------------");

        // 柯里化
        BiaryOperator cary = x -> (y -> (x + y));
        System.out.println(cary.apply(3).apply(4));
        System.out.println("------------");

        System.out.println(compose.apply(f1).apply(f2).apply(2)); //(2^2)*3  f1 f2 2 分别对于compose中的xyz  先执行 f2(2)=4 再执行f1(4)=12
        System.out.println(compose1.apply(f1).apply(f2).apply(2)); //(2*3)^2 f1 f2 2 分别对于compose中的yxz  先执行 f1(2)=6 再执行f2(6)=36
        System.out.println("------------");

        String string  = func().apply("A").apply("B").apply("C").apply("D");
        System.out.println(string);
        System.out.println("------------");
    }

    interface BiaryOperator extends Function<Integer,Function<Integer,Integer>>{}

    Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>> compose =
            x -> y -> z -> (x.apply(y.apply(z)));

    Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>> compose1 =
            x -> y -> z -> (y.apply(x.apply(z)));

    //将以下函数转换成一个柯里化函数
    <A,B,C,D> String func(A a,B b,C c,D d){
        return String.format("%s %s %s %s",a,b,c,d);
    }

    //在转换前,我们只需要知道多层输入的类型和输出,那么就可以简单地写出方法的签名了
    //然后就是实现了,实现起来十分简单,就是单输入柯里化的嵌套
    <A,B,C,D> Function<A,Function<B,Function<C,Function<D,String>>>> func() {
        return a->b->c->d-> String.format("%s %s %s %s",a,b,c,d);
    }
    //应用也很简单




}
