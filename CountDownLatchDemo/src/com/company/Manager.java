package com.company;

import java.util.concurrent.*;

public class Manager {
    private CountDownLatch developersArriveLatch ;
    private CountDownLatch projectStartLatch = new CountDownLatch(1);
    private CountDownLatch developersCompletedLatch;
    private ExecutorService ecs = Executors.newCachedThreadPool();
    private ProjectView projectView;

    Manager(int nDevelopers) {
        developersArriveLatch = new CountDownLatch(nDevelopers);
        developersCompletedLatch = new CountDownLatch(nDevelopers);
        projectView = new ProjectView(nDevelopers);
    }

    public void waitForDevelopersToArrive() throws InterruptedException {
        developersArriveLatch.await();
    }

    public void startProject() {
        projectStartLatch.countDown();
    }

    public void waitForDevelopersToCOmplete() throws InterruptedException {
        developersCompletedLatch.await();
    }

    public void reportArrival() {
        developersArriveLatch.countDown();
    }

    public CountDownLatch getWaitingLatch() {
        return projectStartLatch;
    }

    public void reportDone() {
        developersCompletedLatch.countDown();
    }

    public void startDeveloper(Developer developer) {
        ecs.submit(developer);
    }

    public void shutdown() throws InterruptedException {
        ecs.shutdown();
        ecs.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        projectView.displayProjectCompleted();
    }

    public void updateProgress(int id, int percent) {
        projectView.updateProgress(id, percent);
    }
}
