/**
 * This example demonstrates that a (nasty) thread can just ignore the interruption request
 *
 */
public class CantInterrupt {
	
	public static void main(String[] args) throws InterruptedException {
		Thread thr1 = new Thread() {
			@Override
			public void run() {
				for(;;) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Working");
				}
			}
		};
		
		thr1.start();
		Thread.sleep(2000);
		thr1.interrupt();
		thr1.join();
	}

}
