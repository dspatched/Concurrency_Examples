/**
 * This example demonstrates enum singleton
 *
 */
public class LazyInitRace {
	
	private static class ClientThread extends Thread {
		
		@Override
		public void run() {
			EnumSingleton.INSTANCE.doSomething();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ClientThread().start();
		new ClientThread().start();
		Thread.sleep(1000);
	}
}
