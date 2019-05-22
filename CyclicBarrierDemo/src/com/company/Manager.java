package com.company;

import java.util.concurrent.*;

public class Manager implements Runnable {
    private final int nStages;
    private int curStage;
    private CountDownLatch developersArriveLatch ;
    private CountDownLatch projectStartLatch = new CountDownLatch(1);
    private CyclicBarrier developersCompletedBarriers;
    private ExecutorService ecs = Executors.newCachedThreadPool();
    private ProjectView projectView;

    Manager(int nDevelopers, int nStages) {
        this.nStages = nStages;
        curStage = 1;
        developersArriveLatch = new CountDownLatch(nDevelopers);
        developersCompletedBarriers = new CyclicBarrier(nDevelopers, this);
        projectView = new ProjectView(nDevelopers);
    }

    public void waitForDevelopersToArrive() throws InterruptedException {
        developersArriveLatch.await();
    }

    public void startProject() {
        projectStartLatch.countDown();
    }



    public void reportArrival() {
        developersArriveLatch.countDown();
    }

    public CountDownLatch getWaitingLatch() {
        return projectStartLatch;
    }

    public void reportStageDone() throws BrokenBarrierException, InterruptedException {
        developersCompletedBarriers.await();
    }

    public void startDeveloper(Developer developer) {
        ecs.submit(developer);
    }

    public void shutdown() throws InterruptedException {
        ecs.shutdown();
        ecs.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    public void updateProgress(int id, int percent, int curStage) {
        projectView.updateProgress(id, percent, curStage);
    }

    public int getStage() {
        return curStage;
    }

    public int stageCount() {
        return nStages;
    }

    @Override
    public void run() {

    }
}
