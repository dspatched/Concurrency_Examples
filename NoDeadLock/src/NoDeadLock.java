/**
 * This example demonstrates how to avoid a deadlock by trying to grab all the needed locks at once
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class NoDeadLock {
    static class Programmer {
        private final String name;
        private final Lock lock = new ReentrantLock();

        public Programmer(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public boolean ensureOther(Programmer programmer) {
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = programmer.lock.tryLock();
            } finally {
                if (! (myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        programmer.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }
            
        public void reviewCode(Programmer programmer) {
            if (ensureOther(programmer)) {
                try {
                	System.out.format("%s: reviewing %s's code !%n", 
                            this.name, programmer.getName());
                    programmer.submitCode(this);
                } finally {
                    lock.unlock();
                    programmer.lock.unlock();
                }
            } else {
                System.out.format("%s: Waiting for %s to finish",
                    this.name, programmer.getName());
            }
        }

        public void submitCode(Programmer programmer) {
        	System.out.format("%s: submitting my code to %s for a review!%n", this.name, programmer.getName());
        }
    }

    static class MainLoop implements Runnable {
        private Programmer p1;
        private Programmer p2;

        public MainLoop(Programmer p1, Programmer p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    
        public void run() {
            Random random = new Random();
            for (;;) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {}
                p2.reviewCode(p1);
            }
        }
    }
            

    public static void main(String[] args) {
        final Programmer p1 =
            new Programmer("Vanya");
        final Programmer p2 =
            new Programmer("Sanya");
        new Thread(new MainLoop(p1, p2)).start();
        new Thread(new MainLoop(p2, p1)).start();
    }
}