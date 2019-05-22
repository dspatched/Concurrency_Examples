package com.company;

import java.util.concurrent.Semaphore;

public class PrintersPool {

    private static PrintersPool instance;
    private Semaphore sema = new Semaphore(2, true);

    public static synchronized PrintersPool getInstance() {
        if(instance == null) {
            instance = new PrintersPool();
        }
        return instance;
    }

    public void usePrinter(Secretary secretary) throws InterruptedException {

        log(secretary.getName() + " wants to use printer");
        sema.acquire();
        log(secretary.getName() + " using printer. Available printers: " + sema.availablePermits());
        Thread.sleep(4000);
        sema.release();
        log(secretary.getName() + " released printer");
    }

    private void log(String s) {
        System.out.println(s);
    }
}
