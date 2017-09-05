package com.example.doun.chapter21concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by power on 2017/9/5,005.
 */
class SleepItem{
    public void sleep(){
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch(InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("Exiting SleepItem.sleep()");
    }
}


class SleepBlocked implements Runnable {
    public void run() {
        SleepItem sleepItem = new SleepItem();
        sleepItem.sleep();
    }
}


public class Exercise18 {
    public static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException{
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true); // Interrupts if running
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());
    }
}
