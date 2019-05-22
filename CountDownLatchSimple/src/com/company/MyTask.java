package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

public class MyTask implements Runnable {
    private final CountDownLatch latch;
    private final int id;

    public MyTask(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        latch.countDown();
        System.out.println("Task " + id + " executing");
    }
}
