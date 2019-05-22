package com.company;

import javax.swing.*;
import java.awt.*;

public class DeveloperView extends JProgressBar {
    private static final Color[] COLORS = { Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED, Color.MAGENTA};
    private final int nDeveloper;

    public DeveloperView(int nDeveloper) {
        this.nDeveloper = nDeveloper;
        setValue(0);
        setMaximum(100);
        setStringPainted(true);
        setString("Developer " + nDeveloper + " 0%");
    }

    public void updateProgress(int percent, int curStage) {
        setForeground(COLORS[curStage%COLORS.length]);
        setValue(percent);
        setString("Developer " + nDeveloper + " " + percent + "% Stage " + curStage);
    }
}
