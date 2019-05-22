package com.company;

public class Secretary implements Runnable {
    private String name;

    public Secretary(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for(;;) {
                if(Thread.currentThread().isInterrupted()) throw new InterruptedException();
                PrintersPool.getInstance().usePrinter(this);
                sleep(2000);
            }
        } catch(InterruptedException x) {
            Thread.currentThread().interrupt();
        }
    }

    private void sleep(int ms) throws InterruptedException {
        Thread.sleep(ms);
    }


    public String getName() {
        return name;
    }
}
