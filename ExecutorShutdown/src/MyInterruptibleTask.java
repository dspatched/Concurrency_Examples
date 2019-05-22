public class MyInterruptibleTask implements Runnable {
    @Override
    public void run() {
        Utils.log("Starting a task that runs until the thread terminates");
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }
        }
        Utils.log("Task terminated");
    }
}
