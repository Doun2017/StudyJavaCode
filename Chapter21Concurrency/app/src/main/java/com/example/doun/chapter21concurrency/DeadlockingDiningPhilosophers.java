//: concurrency/DeadlockingDiningPhilosophers.java
// Demonstrates how deadlock can be hidden in a program.
// {Args: 0 5 timeout}
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;

public class DeadlockingDiningPhilosophers {
    static ChopstickQueue chopsticks = new ChopstickQueue();
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 3;
        if (args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
//        Chopstick[] sticks = new Chopstick[size];
//        for (int i = 0; i < size; i++)
//            sticks[i] = new Chopstick();
        for (int i = 0; i < size; i++)
            chopsticks.add(new Chopstick());
        for (int i = 0; i < size; i++)
            exec.execute(new Philosopher(chopsticks, i, ponder));
        if (args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
