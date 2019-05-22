package com.company;

import java.util.ArrayList;
import java.util.List;

public class SWProject {
    private Manager manager ;
    List<Developer> developers = new ArrayList<>();

    public SWProject(int nFeatures) {
        manager = new Manager(nFeatures);
        for(int i=0; i<nFeatures; ++i) {
            developers.add(new Developer(i+1, manager));
        }
    }

    public void start() throws InterruptedException {
        System.out.println("Waiting for develoeprs to arrive");
        manager.waitForDevelopersToArrive();
        manager.startProject();
        manager.waitForDevelopersToCOmplete();
        System.out.println("Project completed");
        manager.shutdown();
    }
}
