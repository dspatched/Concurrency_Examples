package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * Semaphores as mutexes
 * Ping-pong game where thread sync is made using the semaphores
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Semaphore pingSema = new Semaphore(1);
        Semaphore pongSema = new Semaphore(0);
	    PingPongTask pingTask = new PingPongTask("ping", pingSema, pongSema);
	    PingPongTask pongTask = new PingPongTask("pong", pongSema, pingSema);
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(pingTask);
        es.submit(pongTask);
        Thread.sleep(3000);
        pingTask.interrupt();
        pongTask.interrupt();
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

    }
}
