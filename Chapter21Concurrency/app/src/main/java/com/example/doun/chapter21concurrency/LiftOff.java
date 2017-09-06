//: concurrency/LiftOff.java
// Demonstration of the Runnable interface.
package com.example.doun.chapter21concurrency;

import java.util.concurrent.TimeUnit;

public class LiftOff implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {

        //官方答案
//        while(countDown-- > 0) {
//            if(Thread.currentThread().isInterrupted()) {
//                System.out.println("Interrupted: #" + id);
//                return;
//            }
//            System.out.println(status());
//            Thread.yield();
//        }

        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
            try {
                TimeUnit.MILLISECONDS.sleep(100);

            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
                System.out.println("Stopping " + this);
                return;
            }
        }


    }
} ///:~
