package com.company;

import java.util.concurrent.BrokenBarrierException;

/**
 * CyclicBarrier demo. Developers work on project moving to the next project phase
 * when all the development for the current phase has been completed
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
	    SWProject project = new SWProject(5, 3);
	    project.start();

    }
}
