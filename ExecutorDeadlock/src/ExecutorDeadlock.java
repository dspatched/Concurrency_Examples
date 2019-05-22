/**
 * This example demonstrates tasks deadlock in a single thread executor
 * The moral is: tasks shall be the independent units of work
 *
 */
public class ExecutorDeadlock {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		MyEngine.getInstance().submit(new MainTask());
		Thread.sleep(5000);
	}

}
