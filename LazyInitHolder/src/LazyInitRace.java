/**
 * This example demonstrates 'initialization on demand' technique to initialize a singleton
 *
 */
public class LazyInitRace {
	
	private static class ClientThread extends Thread {
		
		@Override
		public void run() {
			LazySingleton.getInstance();	
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ClientThread().start();
		new ClientThread().start();
		Thread.sleep(1000);
	}
}
