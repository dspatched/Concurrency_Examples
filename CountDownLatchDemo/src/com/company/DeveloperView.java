package com.company;

import javax.swing.*;

public class DeveloperView extends JProgressBar {
    private final int nDeveloper;

    public DeveloperView(int nDeveloper) {
        this.nDeveloper = nDeveloper;
        setValue(0);
        setMaximum(100);
        setStringPainted(true);
        setString("Developer " + nDeveloper + " 0%");
    }

    public void updateProgress(int percent) {
        setValue(percent);
        setString("Developer " + nDeveloper + " " + percent + "%");
    }
}
