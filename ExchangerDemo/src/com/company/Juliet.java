package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;

public class Juliet implements Callable<Void> {

    private final Exchanger<Kiss> exchanger;

    Juliet(Exchanger<Kiss> exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public Void call() throws InterruptedException {
        while(!Thread.currentThread().isInterrupted()) {
            Thread.sleep(500);
            Kiss kiss = new Kiss("Juliet");
            Kiss otherKiss = exchanger.exchange(kiss);
            System.out.println("Juliet recieves " + otherKiss);
        }
        return null;
    }
}
