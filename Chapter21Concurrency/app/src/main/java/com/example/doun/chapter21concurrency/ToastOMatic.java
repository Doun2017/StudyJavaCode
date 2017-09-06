//: concurrency/ToastOMatic.java
// A toaster that uses queues.
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.*;

//import static net.mindview.util.Print.*;

class Toast {
    public enum Status {DRY, PEANUT_BUTTERED, JELLY, BUTTERED, JAMMED}

    private Status status = Status.DRY;
    private final int id;

    public Toast(int idn) {
        id = idn;
    }

    public void butter() {
        status = Status.BUTTERED;
    }
    public void peanutButtered() {
        status = Status.PEANUT_BUTTERED;
    }
    public void jelly() {
        status = Status.JELLY;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);

    public Toaster(ToastQueue tq) {
        toastQueue = tq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(
                        100 + rand.nextInt(500));
                // Make toast
                Toast t = new Toast(count++);
                System.out.println(t);
                // Insert into queue
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

// Apply butter to toast:
class Butterer implements Runnable {
    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dry, ToastQueue buttered) {
        dryQueue = dry;
        butteredQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}

// Apply jam to buttered toast:
class Jammer implements Runnable {
    private ToastQueue butteredQueue, finishedQueue;

    public Jammer(ToastQueue buttered, ToastQueue finished) {
        butteredQueue = buttered;
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}

// Apply PeanutButter to toast:
class PeanutButter implements Runnable {
    private ToastQueue dryQueue, peanutButteredQueue;

    public PeanutButter(ToastQueue dry, ToastQueue buttered) {
        dryQueue = dry;
        peanutButteredQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.peanutButtered();
                System.out.println(t);
                peanutButteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("PeanutButter interrupted");
        }
        System.out.println("PeanutButter off");
    }
}

// Apply Jelly to toast:
class Jelly implements Runnable {
    private ToastQueue dryQueue, jellyQueue;

    public Jelly(ToastQueue dry, ToastQueue buttered) {
        dryQueue = dry;
        jellyQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.jelly();
                System.out.println(t);
                jellyQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jelly interrupted");
        }
        System.out.println("Jelly off");
    }
}

// Consume the toast:
class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int counter = 0;

    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = finishedQueue.take();
                // Verify that the toast is coming in order,
                // and that all pieces are getting jammed:
                if (t.getId() != counter++ /*|| t.getStatus() != Toast.Status.JAMMED*/) {
                    System.out.println(">>>> Error: " + t);
                    System.exit(1);
                } else
                    System.out.println("Chomp! " + t);
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}

public class ToastOMatic {
    public static void main(String[] args) throws Exception {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                peanutButteredQueue = new ToastQueue(),
                jellyQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));

        //原始的
//        exec.execute(new Butterer(dryQueue, butteredQueue));
//        exec.execute(new Jammer(butteredQueue, finishedQueue));
//        exec.execute(new Eater(finishedQueue));

        //PeanutButter的
//        exec.execute(new PeanutButter(dryQueue, peanutButteredQueue));
//        exec.execute(new Eater(peanutButteredQueue));

        //Jelly的
//        exec.execute(new Jelly(dryQueue, jellyQueue));
//        exec.execute(new Eater(jellyQueue));

        //PeanutButter+Jelly的
        exec.execute(new PeanutButter(dryQueue, peanutButteredQueue));
        exec.execute(new Jelly(peanutButteredQueue, jellyQueue));
        exec.execute(new Eater(jellyQueue));

        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
