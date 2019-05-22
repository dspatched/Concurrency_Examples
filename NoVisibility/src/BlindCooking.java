import java.util.Random;

/**
 * This example demonstrates (possible) visibility issues when we're not using
 * volatile
 *
 */
public class BlindCooking extends Thread {

	static int cooking = 0;
	
	public static void main(String[] args) throws Exception {
		new Saucer().start();
		new Cooker().start();
	}

	static class Saucer extends Thread {
		@Override
		public void run() {
			while (true) {
				if (cooking >= 5) {
					break;
				}
			}
			System.out.println("Cooking for 5 min, time to add sauce");
		}
	}

	static class Cooker extends Thread {
		@Override
		public void run() {
			while (cooking < 10) {
				System.out.println("Cooking for " + (cooking++) + " min");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
