// This example demonstrates handliong InterruptedException
public class Interview {
	
    static void message(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }

    private static class Candidate
        implements Runnable {
        public void run() {
            String phrases[] = {
                "I know Java",
                "And I know C++",
                "And I've got some experience in Python",
                "And I'm learning Angular, too",
                "Of course since I'm learning Angular I know JavaScript",
                "And I klnwo some Go",
                "And Kotlin",
                "And Algol 68",
                "And COBOL",
                "And name any programming language I know it"
                
            };
            try {
                for (String phrase: phrases) {
                    Thread.sleep(2000);
                    // Print a message
                    message(phrase);
                }
            } catch (InterruptedException e) {
                message("I haven't told you everything!");
            }
        }
    }

    public static void main(String args[])
        throws InterruptedException {
    	
    	Thread.currentThread().setName("Interviewer");

        long patience = 6000;

        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new Candidate(), "Candidate");
        t.start();

        message("Tell me about your experience");
        // loop until MessageLoop
        // thread exits
        while (t.isAlive()) {
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                message("Thanks a lot, we'll let you know");
                t.interrupt();
                t.join();
            }
        }
    }
}
