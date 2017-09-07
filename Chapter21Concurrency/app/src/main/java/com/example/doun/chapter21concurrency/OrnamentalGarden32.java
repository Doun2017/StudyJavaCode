//: concurrency/OrnamentalGarden32.java
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.*;
//import static net.mindview.util.System.out.println.*;

class Entrance32 implements Runnable {
    static final int SIZE = 1000;
    private static List<Entrance32> entrances = new ArrayList<>();
    private static CountDownLatch latch = new CountDownLatch(SIZE);
    private int number = 0;
    // Doesn't need synchronization to read:
    private final int id;
    private static volatile boolean canceled = false;

    private static Random rand = new Random(47);


    // Atomic operation on a volatile field:
    public static void cancel() {
        canceled = true;
    }

    public Entrance32(int id) {
        this.id = id;
        // Keep this task in a list. Also prevents
        // garbage collection of dead tasks:
        entrances.add(this);
    }

    public void run() {
        while (!canceled) {
            if (rand.nextBoolean()) { // Yield half the time
                synchronized (this) {
                    ++number;
                }
                latch.countDown();
                System.out.println(this + " Total: " + (SIZE - latch.getCount()));
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping " + this);
    }


    public String toString() {
        return "Entrance32 " + id + ": " + getValue();
    }

    public synchronized int getValue() {
        return number;
    }

    public static int getTotalCount() {
        return (int)(SIZE - latch.getCount());
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance32 Entrance32 : entrances)
            sum += Entrance32.getValue();
        return sum;
    }
}

public class OrnamentalGarden32 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance32(i));
        // Run for a while, then stop and collect the data:
        TimeUnit.SECONDS.sleep(3);
        Entrance32.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some tasks were not terminated!");
        System.out.println("Total: " + Entrance32.getTotalCount());
        System.out.println("Sum of Entrances: " + Entrance32.sumEntrances());
    }
} /* Output: (Sample)
Entrance32 0: 1 Total: 1
Entrance32 2: 1 Total: 3
Entrance32 1: 1 Total: 2
Entrance32 4: 1 Total: 5
Entrance32 3: 1 Total: 4
Entrance32 2: 2 Total: 6
Entrance32 4: 2 Total: 7
Entrance32 0: 2 Total: 8
...
Entrance32 3: 29 Total: 143
Entrance32 0: 29 Total: 144
Entrance32 4: 29 Total: 145
Entrance32 2: 30 Total: 147
Entrance32 1: 30 Total: 146
Entrance32 0: 30 Total: 149
Entrance32 3: 30 Total: 148
Entrance32 4: 30 Total: 150
Stopping Entrance32 2: 30
Stopping Entrance32 1: 30
Stopping Entrance32 0: 30
Stopping Entrance32 3: 30
Stopping Entrance32 4: 30
Total: 150
Sum of Entrances: 150
*///:~
