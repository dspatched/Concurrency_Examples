package com.company;

import java.util.concurrent.Phaser;

public class MyTask implements Runnable {
    private final Phaser phaser;
    private final int id;

    public MyTask(int id, Phaser phaser) {
        this.id = id;
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Task " + id + " executing");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arriveAndAwaitAdvance();
    }
}
