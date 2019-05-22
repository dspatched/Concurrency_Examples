package com.company;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;

public class MyTask implements Runnable {
    private final int numStages;
    private final int id;
    private final Phaser phaser;
    private final int deregisterStage;
    private final ConcurrentHashMap<Integer, Integer> map;
    private int curStage;

    public MyTask(int id, int numStages, Phaser phaser, int deregisterStage, ConcurrentHashMap<Integer, Integer> map) {
        this.id = id;
        this.numStages = numStages;
        this.phaser = phaser;
        this.deregisterStage = deregisterStage;
        this.map = map;
        curStage = 1;
    }

    @Override
    public void run() {
        phaser.register();
        while(curStage < numStages) {
            System.out.println("Task " + id + " performing stage " + curStage);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(id, new Random().nextInt(5));
            if(curStage == deregisterStage) {
                System.out.println("Task " + id + " deregistering");
                phaser.arriveAndDeregister();
                break;
            } else {
                System.out.println("Task " + id + " advancing");
                phaser.arriveAndAwaitAdvance();
            }
            System.out.println("Task " + id + " done stage " + curStage);
            ++curStage;
        }
    }
}
