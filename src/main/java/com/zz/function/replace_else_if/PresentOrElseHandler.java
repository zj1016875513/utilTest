package com.zz.function.replace_else_if;

import java.util.function.Consumer;

/**
 * 空值与非空值的分支处理
 */
@FunctionalInterface
public interface PresentOrElseHandler<T extends Object> {

    /**
     * @param action 值不为空执行的消费操作
     * @param emptyAction 值为空执行的操作
     */
    void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);
}

