package com.zz.lambda.either;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class either_test1 {
    private Object doSomething(String item) {
        System.out.println("doSomething:\t" + item);
        return item;
    }


    /**###很显然这不是一种好的表现方式##**/
    @Test
    public void t1() {
        List<String> myList = Arrays.asList("1", "2", "3", "4", "5", "6");

        myList.stream().map(item -> {
            try {
                return doSomething(item);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
    }

    private Object trySomething(String item) {
        try {
            return doSomething(item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**将函数体提取到一个单独的方法中，并调用新方法做try-catch处理**/
    @Test
    public void t2() {
        List<String> myList = Arrays.asList("1", "2", "3", "4", "5", "6");
        myList.stream().map(this::trySomething).forEach(System.out::println);
    }

    //定义一个检查接口
    @FunctionalInterface
    public interface CheckedFunction<T,R> {
        R apply(T t) throws Exception;
    }

    public static <T,R> Function<T,R> wrap(CheckedFunction<T,R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**调用公共wrap 进行异常处理*/
    @Test
    public void t3(){
        List<String> myList = Arrays.asList("1", "2", "3", "4", "5", "6");
        myList.stream()
                .map(wrap(item -> doSomething(item)))
                .forEach(System.out::println);
    }



    //只记录异常
    public static <T,R> Function<T, Either> lift(CheckedFunction<T,R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(ex);
            }
        };
    }

    //记录异常和值
    public static <T,R> Function<T, Either> liftWithValue(CheckedFunction<T,R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(Pair.of(ex,t));
            }
        };
    }

    /**调用Either.lift 捕获异常继续执行*/
    @Test
    public void t4(){
        List<String> myList = Arrays.asList("1", "2", "3", "4", "5", "6");
        myList.stream()
                .map(Either.lift(item -> doSomething(item)))
                .forEach(System.out::println);
    }

}
