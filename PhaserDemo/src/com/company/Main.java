package com.company;

/*
 * Another Phaser demo
 * Parties and their register/deregister phases are read from a file schedule.txt
 * (pass the file as a program argument)
 * Then each party bregisters, works and deregisters accordingly
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
	    Project project = Project.createFromFile(args[0]);
	    project.start();
    }

    private static void log(String text) {
    	System.out.println(text);
	}
}
