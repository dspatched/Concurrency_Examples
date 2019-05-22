public class Counter {
	int count;

	public void increment() {
		++count;
	}

	public int getCount() {
		return count;
	}

	public void reset() {
		count = 0;
	}
}