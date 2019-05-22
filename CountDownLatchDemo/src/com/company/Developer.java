package com.company;

import java.util.Random;
import java.util.concurrent.Callable;

public class Developer implements Callable<Boolean> {

    private final Manager manager;
    private final int id;
    private int percent;
    private Random rand = new Random();

    public Developer(int id, Manager manager) {
        this.id = id;
        this.manager = manager;
        manager.startDeveloper(this);
    }

    @Override
    public Boolean call() throws Exception {
        manager.reportArrival();
        waitForStart();
        progress();
        manager.reportDone();
        return true;
    }

    private void progress() throws InterruptedException {
        System.out.println("Working on feature...");
        while(percent < 100) {
            Thread.sleep(getRandomDelay());
            percent += 10;
            manager.updateProgress(id, percent);
        }
        System.out.println("Feature completed");
    }

    private long getRandomDelay() {
        return (rand.nextInt(4) + 1)*500;
    }

    private void waitForStart() throws InterruptedException {
        manager.getWaitingLatch().await();
    }
}
