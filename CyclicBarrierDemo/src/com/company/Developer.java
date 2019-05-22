package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;

public class Developer implements Callable<Boolean> {

    private final Manager manager;
    private final int id;
    private int percent;
    private int curStage = 1;

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
        return true;
    }

    private void progress() throws InterruptedException, BrokenBarrierException {
        while(curStage <= manager.stageCount()) {
            System.out.println("Working on feature... stage " + curStage);
            while (percent < 100) {
                Thread.sleep(500*id);
                percent += 10;
                manager.updateProgress(id, percent, curStage);
            }
            System.out.println("Feature completed");
            manager.reportStageDone();
            ++curStage;
            percent = 0;
        }
    }

    private void waitForStart() throws InterruptedException {
        manager.getWaitingLatch().await();
    }
}
