package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
  * Semaphore demo
  * Secretaries want to print their document but there are only limited number of printers available
  */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Secretary s1 = new Secretary("Tanya");
        Secretary s2 = new Secretary("Vanya");
        Secretary s3 = new Secretary("Lena");
        Secretary s4 = new Secretary("Lida");
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(s1);
        es.submit(s2);
        es.submit(s3);
        es.submit(s4);
        es.submit(s5);
        Thread.sleep(10000);
        es.shutdownNow();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
