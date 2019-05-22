/**
 * This example demonstrates race condition during lazy initialization
 *
 */
public class LazyInitRace {
	
	private static class ClientThread extends Thread {
		
		@Override
		public void run() {
			LazySingleton.getInstance();	
		}
	}
	
	public static void main(String[] args) {
		new ClientThread().start();
		new ClientThread().start();
	}
}
