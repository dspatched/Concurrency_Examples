package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Ping pong game using ReentrantLocks and Conditions to sync the threads
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition pingCond = lock.newCondition();
        Condition pongCond = lock.newCondition();
	    PingPongTask pingTask = new PingPongTask("ping", lock, pingCond, pongCond, true);
	    PingPongTask pongTask = new PingPongTask("pong", lock, pongCond, pingCond, false);
	    pingTask.setOtherTask(pongTask);
	    pongTask.setOtherTask(pingTask);
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(pingTask);
        es.submit(pongTask);
        Thread.sleep(3000);
        pingTask.interrupt();
        pongTask.interrupt();
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("Done");

    }
}
