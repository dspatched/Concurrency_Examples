package com.company;

public class Main {

    /*
     * CountDownLatch demo
     * Developers work on their features while the manager awaits for all the features to be completed
     */
    public static void main(String[] args) throws InterruptedException {
	    SWProject project = new SWProject(5);
	    project.start();

    }
}
