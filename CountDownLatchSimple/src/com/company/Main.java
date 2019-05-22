package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** Simple CountDownLatch demo
 *  The main thread waits for all the tasks to complete, then exits
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(5);
        for(int i=1; i<=5; ++i) {
            es.submit(new MyTask(i, latch));
        }
        latch.await();
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("All executed");
    }
}
