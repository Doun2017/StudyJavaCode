package com.example.doun.chapter21concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Doun on 2017/9/3.
 */

class RandomSleepingTask implements Runnable {
    private static Random random = new Random(39);

    private static int taskCount = 0;
    private final int id = taskCount++;

    public RandomSleepingTask() {
        System.out.println(id+" created");

    }

    public void run() {
        int sleeptime = random.nextInt(10);
        try {
            // Old-style:
            // Thread.sleep(100);
            // Java SE5/6-style:
            TimeUnit.MILLISECONDS.sleep(1000 * sleeptime);
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
        System.err.println(id+"sleeptime"+sleeptime);
    }
}


public class Exercise6 {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new RandomSleepingTask());
        exec.shutdown();
    }
}
