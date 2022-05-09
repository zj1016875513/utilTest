package com.zz.function.replace_else_if;

@FunctionalInterface
public interface BranchHandle {

    /**
     *
     * @param trueHandle 为true时，要进行的操作
     * @param falseHandle false时的操作
     */
    void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);

}

