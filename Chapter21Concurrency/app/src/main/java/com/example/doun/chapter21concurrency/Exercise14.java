package com.example.doun.chapter21concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by power on 2017/9/4,004.
 */

public class Exercise14 {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i=0; i<1000; i++){
            new Timer().schedule(new TimerTask() {
                public void run() {
                    System.err.print(" "+atomicInteger.addAndGet(1));
//                    System.exit(0);
                }
            }, i*1);
        }
    }
}
