package com.zz.async;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author mghio
 * @since 2021-08-01
 */
public class CompletableFutureDemo {

    public static CompletableFuture<String> doOneThing() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "doOneThing";
        });
    }

    public static CompletableFuture<String> doOtherThing(String parameter) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return parameter + " " + "doOtherThing";
        });
    }

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        StopWatch stopWatch = new StopWatch("CompletableFutureDemo");
//        stopWatch.start();
//
//        // 异步执行版本
//        testCompletableFuture();
//
//        stopWatch.stop();
//        System.out.println(stopWatch);
//    }

    private static void testCompletableFuture() throws InterruptedException, ExecutionException {
        // 先执行 doOneThing 任务，后执行 doOtherThing 任务
        CompletableFuture<String> resultFuture = doOneThing().thenCompose(CompletableFutureDemo::doOtherThing);

        // 获取任务结果
        String doOneThingResult = resultFuture.get();

        // 获取执行结果
        System.out.println("DoOneThing and DoOtherThing execute finished. result = " + doOneThingResult);
    }

}
