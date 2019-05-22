package com.company;

import java.util.concurrent.Callable;

public class MyTask2 implements Callable<String> {
    private static int count;
    private int ndx;

    public MyTask2() {
        ndx = ++count;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Task " + ndx + " running in thread " + Thread.currentThread().getName());
        Thread.sleep(2000);
        return "done";
    }
}
