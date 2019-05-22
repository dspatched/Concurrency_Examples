import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyEngine {
	
	private static ExecutorService exec = Executors.newFixedThreadPool(1);
	private static MyEngine instance;
	
	public static synchronized MyEngine getInstance() {
		if(instance == null) {
			instance = new MyEngine();
		}
		return instance;
	}

	public Future<Boolean> submit(Callable<Boolean> task) {
		return exec.submit(task);
	}
}
