import java.util.Date;

public class StopWatchLocal {

	private ThreadLocal<Long> started = ThreadLocal.withInitial(() -> 0L);
	private ThreadLocal<Long> stopped = ThreadLocal.withInitial(() -> 0L);;

	public void start() {
		started.set(System.currentTimeMillis());
	}

	public void stop() {
		stopped.set(System.currentTimeMillis());
		
	}

	public String getStarted() {
		return new Date(started.get()).toString();
	}

	public String getEnded() {
		return new Date(stopped.get()).toString();
	}

}
