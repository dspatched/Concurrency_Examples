import java.util.concurrent.Callable;


public class MyTask implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        long start = System.currentTimeMillis();
        Utils.log("Task started");
        Thread.sleep(5000);
        Utils.log("Task completed");
        long end = System.currentTimeMillis();
        return end - start;
    }
}
