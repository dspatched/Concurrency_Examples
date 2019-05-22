import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
// This examples demonstrates a deadlock in a SingleThreadExecutor
// due to the tasks not being completely independent of each other 
public class MainTask implements Callable<Boolean> {

	@Override
	public Boolean call() throws InterruptedException, ExecutionException {
		System.out.println("Starting main task");
		Subtask subtask = new Subtask();
		Future<Boolean> result = MyEngine.getInstance().submit(subtask);
		System.out.println("Waiting for subtask result");
		result.get();
		System.out.println("Completed main task");
		return true;
	}

}
