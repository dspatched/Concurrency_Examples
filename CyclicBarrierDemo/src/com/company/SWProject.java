package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

public class SWProject {
    private Manager manager ;
    List<Developer> developers = new ArrayList<>();

    public SWProject(int nFeatures, int nStages) {
        manager = new Manager(nFeatures, nStages);
        for(int i=0; i<nFeatures; ++i) {
            developers.add(new Developer(i+1, manager));
        }
    }

    public void start() throws InterruptedException, BrokenBarrierException {
        System.out.println("Waiting for develoeprs to arrive");
        manager.waitForDevelopersToArrive();
        manager.startProject();
        //System.out.println("Project completed");
        //manager.shutdown();
    }
}
