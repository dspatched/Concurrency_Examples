
public class MyThread extends Thread {
	
	private boolean isDemon;

	public MyThread(boolean isDemon) {
		this.isDemon = isDemon;
		setDaemon(isDemon);
	}

	@Override
	public void run() {
		while(!isInterrupted()) {
			System.out.println("I'm a " + (isDemon ? "demon" : "user") + " thread");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("I'm interrupted");
				Thread.currentThread().interrupt();
			}
		}
	}

}
