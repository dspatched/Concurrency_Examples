public class CountingThread extends Thread {

	private int from;
	private int to;
	private Counter counter;

	public CountingThread(int from, int to, Counter counter) {
		this.from = from;
		this.to = to;
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = from; i <= to; ++i) {
			if (isPrime(i)) {
				counter.increment();
			}
		}
	}

	private static boolean isPrime(int n) {
		if (n == 1)
			return false;
		if (n == 2)
			return true;
		for (int i = 2; i < n; ++i) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}