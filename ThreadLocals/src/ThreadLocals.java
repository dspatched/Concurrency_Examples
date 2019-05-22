/**
 * This example shows the usage of ThreadLocals
 *
 */
public class ThreadLocals {
	
	
	public static void main(String[] args) {
		final StopWatch sw = new StopWatch();
		for(int i=1; i<=4; ++i) {
			Thread t = new Thread() {

				@Override
				public void run() {
					sw.start();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sw.stop();
					System.out.println("Thread " + getName() + " started " + sw.getStarted() + " ended " + sw.getEnded());
				}
				
			};
			t.start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
