package com.company;

import java.util.Scanner;
import java.util.concurrent.Phaser;

public class Employee implements Runnable {
    private final String name;
    private final int firstPhase;
    private final int lastPhase;
    private final Phaser phaser;
    private int percent;

    private Employee(String name, int firstPhase, int lastPhase, Phaser phaser) {
        this.name = name;
        this.firstPhase = firstPhase;
        this.lastPhase = lastPhase;
        this.phaser = phaser;
    }

    public static Employee createFromRecord(String line, Phaser phaser) {
        Scanner scanner = new Scanner(line);
        String name = scanner.next();
        int firstPhase = Integer.valueOf(scanner.next()) - 1;
        int lastPhase = Integer.valueOf(scanner.next()) - 1;
        return new Employee(name, firstPhase, lastPhase, phaser);
    }

    @Override
    public void run() {
        while(phaser.getPhase() <= lastPhase) {
            doWork();
            log(name + " completed phase " + (phaser.getPhase()+1) + " and waits for others");
            phaser.arriveAndAwaitAdvance();
        }
        phaser.arriveAndDeregister();
        log(name + " deregistered");
    }

    private void doWork() {
        System.out.println(name + " works on phase " + (phaser.getPhase()+1));
        sleep(500);
        System.out.println(name + " completed phase " + (phaser.getPhase()+1));
    }

    private static void log(String s) {
        System.out.println(s);
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", firstPhase=" + firstPhase +
                ", lastPhase=" + lastPhase +
                '}';
    }

    public int getStartPhase() {
        return firstPhase;
    }

    public void register() {
        phaser.register();
    }

    public String getName() {
        return name;
    }
}
