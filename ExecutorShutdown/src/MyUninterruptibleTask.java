import java.util.concurrent.Callable;

public class MyUninterruptibleTask implements Runnable {
    @Override
    public void run()  {
        Utils.log("Starting a task that runs forever");
        long start = System.currentTimeMillis();
        for(;;) {
            Thread.yield();
            long end = System.currentTimeMillis();
            if(end - start > 5000) {
                Utils.log("Task still running");
                start = System.currentTimeMillis();
            }
        }
    }
}
