package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class PingPongTask implements Callable<Void> {
    private final String word;
    private final Semaphore sema;
    private final Semaphore otherSema;
    private volatile boolean interrupted = false;

    public PingPongTask(String word, Semaphore mySema, Semaphore otherSema) {
        this.word = word;
        this.sema = mySema;
        this.otherSema = otherSema;
    }

    public void print() {
        System.out.println(word);
    }

    @Override
    public Void call() throws InterruptedException {
        while(!interrupted) {
            acquire();
            print();
            release();
        }
        return null;
    }

    private void acquire() throws InterruptedException {
        sema.acquire();
    }

    private void release() {
        otherSema.release();
    }

    public void interrupt() {
        interrupted = true;
    }
}
