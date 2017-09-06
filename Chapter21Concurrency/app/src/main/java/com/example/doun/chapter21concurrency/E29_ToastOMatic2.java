package com.example.doun.chapter21concurrency;

/**
 * Created by power on 2017/9/6,006.
 */
//: concurrency/E29_ToastOMatic2.java
/********************** Exercise 29 ***********************
 * Modify ToastOMatic.java to create peanut butter and jelly
 * on Toast1 sandwiches using two separate assembly lines
 * (one for peanut butter, the second for jelly, then
 * merging the two lines).
 *********************************************************/

import java.util.concurrent.*;
import java.util.*;

class Toast1 {
    public enum Status {
        DRY,
        BUTTERED,
        JAMMED,
        READY {
            public String toString() {
                return
                        BUTTERED.toString() + " & " + JAMMED.toString();
            }
        }
    }

    private Status status = Status.DRY;
    private final int id;

    public Toast1(int idn) {
        id = idn;
    }

    public void butter() {
        status = (status == Status.DRY) ? Status.BUTTERED : Status.READY;
    }

    public void jam() {
        status = (status == Status.DRY) ? Status.JAMMED : Status.READY;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast1 " + id + ": " + status;
    }
}

class ToastQueue1 extends LinkedBlockingQueue<Toast1> {
}

class Toaster1 implements Runnable {
    private ToastQueue1 ToastQueue1;
    private int count;
    private Random rand = new Random(47);

    public Toaster1(ToastQueue1 tq) {
        ToastQueue1 = tq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                // Make Toast1
                Toast1 t = new Toast1(count++);
                System.out.println(t);
                // Insert into queue
                ToastQueue1.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster1 interrupted");
        }
        System.out.println("Toaster1 off");
    }
}

// Apply butter to Toast1:
class Butterer1 implements Runnable {
    private ToastQueue1 inQueue, butteredQueue;

    public Butterer1(ToastQueue1 in, ToastQueue1 buttered) {
        inQueue = in;
        butteredQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of Toast1 is available:
                Toast1 t = inQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer1 interrupted");
        }
        System.out.println("Butterer1 off");
    }
}

// Apply jam to Toast1:
class Jammer1 implements Runnable {
    private ToastQueue1 inQueue, jammedQueue;

    public Jammer1(ToastQueue1 in, ToastQueue1 jammed) {
        inQueue = in;
        jammedQueue = jammed;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of Toast1 is available:
                Toast1 t = inQueue.take();
                t.jam();
                System.out.println(t);
                jammedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer1 interrupted");
        }
        System.out.println("Jammer1 off");
    }
}

// Consume the Toast1:
class Eater1 implements Runnable {
    private ToastQueue1 finishedQueue;

    public Eater1(ToastQueue1 finished) {
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of Toast1 is available:
                Toast1 t = finishedQueue.take();
                // Verify that all pieces are ready for consumption:
                if (t.getStatus() != Toast1.Status.READY) {
                    System.out.println(">>>> Error: " + t);
                    System.exit(1);
                } else
                    System.out.println("Chomp! " + t);
            }
        } catch (InterruptedException e) {
            System.out.println("Eater1 interrupted");
        }
        System.out.println("Eater1 off");
    }
}

// Outputs alternate inputs on alternate channels:
class Alternator implements Runnable {
    private ToastQueue1 inQueue, out1Queue, out2Queue;
    private boolean outTo2; // control alternation

    public Alternator(ToastQueue1 in, ToastQueue1 out1,
                      ToastQueue1 out2) {
        inQueue = in;
        out1Queue = out1;
        out2Queue = out2;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of Toast1 is available:
                Toast1 t = inQueue.take();
                if (!outTo2)
                    out1Queue.put(t);
                else
                    out2Queue.put(t);
                outTo2 = !outTo2; // change state for next time
            }
        } catch (InterruptedException e) {
            System.out.println("Alternator interrupted");
        }
        System.out.println("Alternator off");
    }
}

// Accepts toasts on either channel, and relays them on to
// a "single" successor
class Merger implements Runnable {
    private ToastQueue1 in1Queue, in2Queue, toBeButteredQueue,
            toBeJammedQueue, finishedQueue;

    public Merger(ToastQueue1 in1, ToastQueue1 in2,
                  ToastQueue1 toBeButtered, ToastQueue1 toBeJammed,
                  ToastQueue1 finished) {
        in1Queue = in1;
        in2Queue = in2;
        toBeButteredQueue = toBeButtered;
        toBeJammedQueue = toBeJammed;
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of Toast1 is available:
                Toast1 t = null;
                while (t == null) {
                    t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
                    if (t != null)
                        break;
                    t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
                }
                // Relay Toast1 onto the proper queue
                switch (t.getStatus()) {
                    case BUTTERED:
                        toBeJammedQueue.put(t);
                        break;
                    case JAMMED:
                        toBeButteredQueue.put(t);
                        break;
                    default:
                        finishedQueue.put(t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Merger interrupted");
        }
        System.out.println("Merger off");
    }
}

public class E29_ToastOMatic2 {
    public static void main(String[] args) throws Exception {
        ToastQueue1
                dryQueue = new ToastQueue1(),
                butteredQueue = new ToastQueue1(),
                toBeButteredQueue = new ToastQueue1(),
                jammedQueue = new ToastQueue1(),
                toBeJammedQueue = new ToastQueue1(),
                finishedQueue = new ToastQueue1();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster1(dryQueue));
        exec.execute(new Alternator(dryQueue, toBeButteredQueue, toBeJammedQueue));
        exec.execute(new Butterer1(toBeButteredQueue, butteredQueue));
        exec.execute(new Jammer1(toBeJammedQueue, jammedQueue));
        exec.execute(new Merger(butteredQueue, jammedQueue, toBeButteredQueue, toBeJammedQueue, finishedQueue));
        exec.execute(new Eater1(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~