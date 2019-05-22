package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPongTask implements Callable<Void> {
    private final String word;
    private final ReentrantLock lock;
    private final Condition my;
    private final Condition other;
    private final int myId;
    private final boolean isOwner;
    private PingPongTask otherTask;
    private PingPongTask currentOwner;
    private volatile boolean interrupted = false;
    private static volatile int id = 1;
    private Thread thread;

    public PingPongTask(String word, ReentrantLock lock, Condition my, Condition other, boolean isOwner) {
        this.word = word;
        this.lock = lock;
        this.my = my;
        this.other = other;
        this.isOwner = isOwner;
        this.myId = id++;
    }

    public void setOtherTask(PingPongTask otherTask) {
        this.otherTask = otherTask;
        this.currentOwner = isOwner ? this : otherTask;
    }

    public void print() throws InterruptedException {
        Thread.sleep(500);
        System.out.println(word);
    }

    @Override
    public Void call() throws InterruptedException {
        thread = Thread.currentThread();
        log("thread=" + thread);
        try {
            while (!thread.isInterrupted()) {
                acquire();
                print();
                release();
            }
        } catch(InterruptedException x) {
            log("Interrupted " + thread);
            thread.interrupt();
        }
        return null;
    }

    private void acquire() throws InterruptedException {
        lock.lockInterruptibly();

        try {
            while (currentOwner != this) {
                log(myId + " awaits other=" + otherTask.myId + " currentOwnerId=" + currentOwner.myId);
                my.await();
            }
        }  finally {
            lock.unlock();
        }
    }

    private void log(String s) {
        //System.out.println(s);
    }

    private void release() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            log(myId + " signals otherId=" + otherTask.myId + " currentOwnerId=" + currentOwner.myId);
            otherTask.currentOwner = otherTask;
            currentOwner = otherTask;
            other.signal();
        } finally {
            lock.unlock();
        }
    }

    public void interrupt() {
        log("Interrupting " + thread);
        thread.interrupt();
    }
}
