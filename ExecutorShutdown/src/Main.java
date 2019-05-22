import java.util.List;
import java.util.concurrent.*;

/** Demonstrates various scenarios of ExecutorService shutdowns
 *  (and why it sometimes doesn't properly shutdown unless we handle thread interruption requests)
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        shutdown1();
        //shutdown2();
        //shutdown3();
        //shutdown4();
        //shutdown5();
    }

    private static void shutdown1() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Long> taskFuture = es.submit(new MyTask());
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        Utils.log("Task result: " + taskFuture.get());
    }

    private static void shutdown2() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new MyUninterruptibleTask());
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        Utils.log("awaitTermination returned");
    }

    private static void shutdown3() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new MyUninterruptibleTask());
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        Utils.log("awaitTermination returned");
        List<Runnable> notExecutedTaks = es.shutdownNow();
        Utils.log("shutdownNow returned " + notExecutedTaks);
    }

    private static void shutdown4() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor es = (ThreadPoolExecutor)Executors.newCachedThreadPool();
        es.setThreadFactory(new DaemonThreadFactory());
        es.execute(new MyUninterruptibleTask());
        Thread.sleep(10000);
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        Utils.log("awaitTermination returned");
        List<Runnable> notExecutedTaks = es.shutdownNow();
        Utils.log("shutdownNow returned " + notExecutedTaks);
    }

    private static void shutdown5() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new MyInterruptibleTask());
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        Utils.log("awaitTermination returned");
        List<Runnable> notExecutedTaks = es.shutdownNow();
        Utils.log("shutdownNow returned " + notExecutedTaks);
    }
}
