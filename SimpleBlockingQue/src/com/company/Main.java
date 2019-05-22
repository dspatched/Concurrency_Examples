package com.company;

import java.util.IdentityHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Custom bounded queue implementaion using Lock and Conditions
 */
public class Main {

    private static int n;
    private static BoundedBuffer que = new BoundedBuffer();

    public static void main(String[] args) throws InterruptedException {
	    Runnable producer = () -> {
	        while(!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500);
                    System.out.println("Putting " + n);
                    que.put(n++);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        Runnable consumer = () -> {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500);
                    int n = que.take();
                    System.out.println("Took " + (n));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(producer);
        es.execute(consumer);
        Thread.sleep(5000);
        es.shutdownNow();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
