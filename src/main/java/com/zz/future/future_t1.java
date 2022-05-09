package com.zz.future;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class future_t1 {
    /**
     * jdk1.8之前的Future
     * @author Administrator
     */
    @Test
    //这种还是同步
    public void t11() throws Throwable, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<String> f = executor.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("task started!");
//                    longTimeMethod();
                TimeUnit.SECONDS.sleep(2);
                System.out.println("task finished!");
                return "hello";
            }
        });

        //此处get()方法阻塞main线程
        System.out.println(f.get());
        System.out.println("main thread is blocked");
    }

    @Test
    //这个异步
    public void t12() throws Throwable, ExecutionException {
        // 两个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //jdk1.8之前的实现方式
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("task started!");
                try {
                    //模拟耗时操作
//                    longTimeMethod();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "task finished!";
            }
        }, executor);

        //采用lambada的实现方式
        future.thenAccept(e -> System.out.println(e + " ok"));

        System.out.println("main thread is running");
        TimeUnit.SECONDS.sleep(3);
    }

}
