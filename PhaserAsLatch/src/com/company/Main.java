package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates using Phase as a CountDownLatch
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        Phaser phaser = new Phaser(1);
        for(int i=1; i<=5; ++i) {
            es.submit(new MyTask(i, phaser));
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("All done");
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
