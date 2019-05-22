package com.company;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* 
 * Exchanger demo
 * Romeo and Juliet exchange their kisses
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<Kiss> exchanger = new Exchanger<>();
        Romeo romeo = new Romeo(exchanger);
        Juliet juliet = new Juliet(exchanger);
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(romeo);
        es.submit(juliet);
        Thread.sleep(3000);
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
