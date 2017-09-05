package com.example.doun.chapter21concurrency;

/**
 * Created by power on 2017/9/5,005.
 */
/****************** Exercise 19 ************************
 * Modify OrnamentalGarden.java so that it uses
 * interrupt().
 ******************************************************/

import java.util.concurrent.*;
import java.util.*;

//import static net.mindview.util.System.out.println.*;
class Entrance2 implements Runnable {
    private static Count count = new Count();
    private static List<Entrance2> entrances = new ArrayList<Entrance2>();
    private int number;
    private final int id;

    public Entrance2(int id) {
        this.id = id;
        entrances.add(this);
    }

    public void run() {
        for (; ; ) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Stopping " + this);
                return;
            }
        }
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance2 entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

public class E19_OrnamentalGarden2 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance2(i));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some tasks were not terminated!");
        System.out.println("Total: " + Entrance2.getTotalCount());
        System.out.println("Sum of Entrances: " + Entrance2.sumEntrances());
    }
} /* Output: (Sample)
Entrance 1: 1 Total: 2
Entrance 2: 1 Total: 3
Entrance 3: 1 Total: 4
Entrance 4: 1 Total: 5
...
Entrance 2: 31 Total: 152
Entrance 3: 31 Total: 153
Entrance 4: 31 Total: 154
Entrance 0: 31 Total: 155
Terminating Entrance 0: 31
Terminating Entrance 4: 31
Terminating Entrance 3: 31
Terminating Entrance 2: 31
Terminating Entrance 1: 31
Total: 155
Sum of Entrances: 155
*///:~
