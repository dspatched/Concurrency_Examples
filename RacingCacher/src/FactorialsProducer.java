import java.util.concurrent.atomic.AtomicLong;

public class FactorialsProducer {
	
	private AtomicLong lastVal = new AtomicLong(0);
	private AtomicLong lastResult = new AtomicLong(0);

	public long getFactorial(int val) {
		if(val == lastVal.get()) {
			return lastResult.get();
		}
		else {
			lastVal.set(val);
			lastResult.set(factorial(val)); 
			return lastResult.get();
		}
	}

	private long factorial(long n) {
		try { Thread.sleep(1000); } catch(InterruptedException x) {}
		long val = 1;
		for(int i=1; i<=n; ++i) {
			val *= i;
		}
		return val;
	}
	
}
