package com.example.doun.chapter21concurrency;

/**
 * Created by Doun on 2017/9/5.
 */

import java.util.concurrent.*;

public class E22_WaitNotify {
    public static void main(String[] args) throws Exception {
        final Runnable r1 = new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        synchronized (this) {
                            notify();
                        }
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        };
        Runnable r2 = new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        synchronized (r1) {
                            r1.wait();
                        }
                    } catch (InterruptedException e) {
                        return;
                    }
                    System.out.print("Cycled ");
                }
            }
        };
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r1);
        exec.execute(r2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }
} /* Output: (Sample)
Cycled Cycled Cycled Cycled Cycled Cycled Cycled Cycled Cycled Cycled
*///:~
