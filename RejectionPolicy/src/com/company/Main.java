package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the predefined Executor rejection policies
 */
public class Main {

    public static void main(String[] args) {
        demo1();
//        demo2();
//        demo3();
//        demo4();
//        demo5();
    }

    private static void demo1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(2));
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.shutdownNow();
    }

    private static void demo2() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.shutdownNow();
    }

    private static void demo3() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.shutdownNow();
    }

    private static void demo4() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.shutdownNow();
    }

    private static void demo5() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        executor.submit(new MyTask2());
        executor.submit(new MyTask2());
        executor.submit(new MyTask2());
        executor.submit(new MyTask2());
        executor.shutdown();
    }

}
