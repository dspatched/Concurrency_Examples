package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectView extends JFrame {
    private final int nDevelopers;
    private java.util.List<DeveloperView> devViews = new ArrayList<>();

    public ProjectView(int nDevelopers) {
        this.nDevelopers = nDevelopers;
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(nDevelopers, 1));
        for(int i=1; i<=nDevelopers; ++i) {
            DeveloperView dv = new DeveloperView(i);
            add(dv);
            devViews.add(dv);
        }
        setVisible(true);
    }

    public void updateProgress(int id, int percent, int curStage) {
        devViews.get(id-1).updateProgress(percent, curStage);
    }
}
