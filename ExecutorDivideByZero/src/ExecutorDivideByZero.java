/**
 * This examples shows that we need to dereference execution result to get a (possible) exception
 */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDivideByZero {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		divideWithThread();
		divideWithExecutor();

	}

	private static void divideWithThread() {
		new Thread(() -> {
			int x = 1/0;
		}).start();
	}

	private static void divideWithExecutor() throws InterruptedException, ExecutionException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<Integer> f = exec.submit(() -> 1/0);
//		int val = f.get();
	}

}
