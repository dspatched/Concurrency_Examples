package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * This example demonstrates throwing ConcurrentModificationEsception by the non thread-safe lists
 * It also demonstrates that the CopyOnWriteArrayList iterator is not guaranteed to see any subsequent list updates
 */
public class Main {

    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
//      private static List<String> list = new ArrayList<>();
//      private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    private static Callable<Void> adder = () -> {
        Thread.sleep(2000);
        list.add("Item 4");
        return null;
    };

    private static Callable<Void> traverser = () -> {
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
            Thread.sleep(6000);
        }
        System.out.println("Done");
        return null;
    };


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        ExecutorService es = Executors.newCachedThreadPool();
        Future<Void> f1 = es.submit(traverser);
        Future<Void> f2 = es.submit(adder);
        f1.get();
        f2.get();
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
