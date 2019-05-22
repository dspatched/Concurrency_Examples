package com.company;

import java.util.concurrent.*;

/**
 *  simple Phaser example. Tasks register themselves at the Phaser as they are created
 *  Then each task deregisters at a certain phase
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
	    ExecutorService es = Executors.newCachedThreadPool();
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap();
	    int[] deregisterStages = { 5, 1, 2, 3, 3};
	    Phaser phaser = new Phaser();
	    for(int i=0; i<5; ++i) {
	        es.submit(new MyTask(i+1, 5, phaser, deregisterStages[i], map));
        }
	    es.shutdown();
	    es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	    System.out.println("All done");
    }
}
