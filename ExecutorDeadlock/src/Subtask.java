import java.util.concurrent.Callable;

public class Subtask implements Callable<Boolean> {

	@Override
	public Boolean call() throws Exception {
		System.out.println("Executing subtask");
		Subtask subtask = new Subtask();
		return null;
	}

}
