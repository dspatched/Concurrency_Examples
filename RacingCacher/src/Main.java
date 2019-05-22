/**
 * This examples shows that a compound state should be guarded by a lock as a whole
 * In this example a state consists of two integers, each guarded separately
 * However, they are n
 *
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		FactorialsProducer producer = new FactorialsProducer(); 
		ClientThread t1 = new ClientThread(producer, "Thread1"); 
		ClientThread t2 = new ClientThread(producer, "Thread2");
		
		t1.start();
		t2.start();
		Thread.sleep(5000);
		t1.interrupt();
		t2.interrupt();
		t1.join();
		t2.join();
		System.out.println("Done");
	}

}
