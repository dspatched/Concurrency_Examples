 // This examples demonstrates that any demon threads terminate when JVM exits
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new MyThread(false);
		Thread t2 = new MyThread(true);
		
		t1.start();
		t2.start();
		Thread.sleep(2000);
		t1.interrupt();
		System.out.println("Main thread exits");
	}

}
