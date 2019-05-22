package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Project {

    private static List<Employee> employees = new ArrayList<>();
    private ExecutorService ecs = Executors.newCachedThreadPool();

    private Phaser phaser = new Phaser(1) {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
//            log("onAdvance " + phase);
            return super.onAdvance(phase, registeredParties);
        }
    };

    public static Project createFromFile(String fname) {
        Project project = new Project();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fname));
            String line;
            while((line = reader.readLine()) != null) {
                line = line.trim();
                if(line.isEmpty()) continue;
                Employee e = Employee.createFromRecord(line, project.phaser);
                employees.add(e);
            }
        } catch(Exception x) {
            throw new RuntimeException(x);
        }
        return project;
    }

    private void registerForPhase(int phase) {
        for(Employee e : employees) {
            if(e.getStartPhase() == phase) {
                log(e.getName() + " registers for phase " + (phase+1));
                e.register();
                ecs.submit(e);
            }
        }
    }

    public void start() throws InterruptedException {
        for(int curStage = 0; curStage < 5; ++curStage) {
            registerForPhase(curStage);
            phaser.arriveAndAwaitAdvance();
        }
        ecs.shutdown();
        ecs.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    private void log(String s) {
        System.out.println(s);
    }

}
