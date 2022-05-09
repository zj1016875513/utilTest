package com.zz.function.replace_else_if;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class F_Utils {


    // 替代else if
    @Test
    public void t1(){
        F_Utils.isTrueOrFalse(true).trueOrFalseHandle(
                () -> System.out.println("true，执行操作"),
                () -> System.out.println("false，不执行操作"));
    }

    @Test
    public void t2(){
        F_Utils.isBlankOrNoBlank("hello world").presentOrElseHandle(
                System.out::println,
                () -> System.out.println("空字符串！"));

        F_Utils.isBlankOrNoBlank("  ").presentOrElseHandle(
                System.out::println,
                () -> System.out.println("空字符串！"));

    }

    public static BranchHandle isTrueOrFalse(Boolean b) {
        return (trueHandle, falseHandle) -> {
            // b为true,执行trueHandler.run,否则执行falseHandler.run
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {
        return (action, emptyAction) -> {
            if (StringUtils.isNotBlank(str)) {
                action.accept(str);
            } else {
                emptyAction.run();
            }
        };
    }



}


