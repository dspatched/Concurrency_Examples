/**
 * This example demonstrates deadlock between two threads when the locks are being taken out of order
 *
 */
public class Deadlock {
	
	private static Object lockA = new Object(); 
	private static Object lockB = new Object(); 
	
	public static void main(String args[]) throws InterruptedException {
		Thread t1 = new Thread(() ->  {
			log("Thread 1 waiting for lock A");
			sleep(50);
			synchronized(lockA) {
				log("Thread 1 waiting for lock B");
				sleep(100);
				synchronized (lockB) {
				}
			}
		});
		Thread t2 = new Thread(() ->  {
			log("Thread 2 waiting for lock B");
			sleep(100);
			synchronized(lockB) {
				log("Thread 2 waiting for lock A");
				sleep(50);
				synchronized (lockA) {
					
				}
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Done");
	}
	
	private static void log(String s) {
		System.out.println(s);
	}
	
	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
