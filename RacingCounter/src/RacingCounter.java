/**
 * This example demonstrates race condition when accessing a shared variable from two threads
 * without a proper synchronization
 * Change count to atomic to fix
 *
 */
public class RacingCounter {
	
	private static Counter counter = new Counter();
	
	public static void main(String[] args) throws InterruptedException {

		serialCounting();
		System.out.println("Primes count (single thread): " + counter.getCount());
		
		counter.reset();
		
		parallelCounting();
		System.out.println("Primes count (parallel threads): " + counter.getCount());
		
	}

	private static void serialCounting() throws InterruptedException {
		CountingThread thread = new CountingThread(1, 10000, counter);
		thread.start();
		thread.join();
	}

	private static void parallelCounting() throws InterruptedException {
		CountingThread thread1 = new CountingThread(1, 5000, counter);
		CountingThread thread2 = new CountingThread(5001, 10000, counter);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}


}
