package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;

public class Romeo implements Callable<Void> {

    private final Exchanger<Kiss> exchanger;

    Romeo(Exchanger<Kiss> exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public Void call() throws InterruptedException {
        while(!Thread.currentThread().isInterrupted()) {
            Thread.sleep(500);
            Kiss kiss = new Kiss("Romeo");
            Kiss otherKiss = exchanger.exchange(kiss);
            Utils.log("Romeo recieves " + otherKiss);
        }
        return null;
    }
}
