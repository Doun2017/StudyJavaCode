//: concurrency/TestBlockingQueues.java
// {RunByHand}
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.io.*;
//import static net.mindview.util.Print.*;

class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }

    public void add(LiftOff lo) {
        try {
            rockets.put(lo);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during put()");
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch (InterruptedException e) {
            System.out.println("Waking from take()");
        }
        System.out.println("Exiting LiftOffRunner");
    }
}

public class TestBlockingQueues {
    static void getkey() {
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getkey(String message) {
        System.out.println(message);
        getkey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        System.out.println(msg);
        final LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();


        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 5; i++){
                    runner.add(new LiftOff(5));
                }

            }
        }).start();

//        for (int i = 0; i < 5; i++)
//            runner.add(new LiftOff(5));



        getkey("Press 'Enter' (" + msg + ")");
        t.interrupt();
        System.out.println("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());// Unlimited size

        test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));// Fixed size

        test("SynchronousQueue", new SynchronousQueue<LiftOff>());// Size of 1

    }
} ///:~
