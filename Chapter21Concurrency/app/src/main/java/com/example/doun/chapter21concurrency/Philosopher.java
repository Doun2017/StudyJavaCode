//: concurrency/Philosopher.java
// A dining philosopher
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.*;
//import static net.mindview.util.System.out.println.*;


class ChopstickQueue extends LinkedBlockingQueue<Chopstick> {
}

public class Philosopher implements Runnable {
    ChopstickQueue chopsticks;
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 10));
    }

    public Philosopher(ChopstickQueue chopsticks, int ident, int ponder) {
        this.chopsticks = chopsticks;
        id = ident;
        ponderFactor = ponder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                // Philosopher becomes hungry
                System.out.println(this + " " + "grabbing one");
                left = chopsticks.take();
                left.take();
                System.out.println(this + " " + "grabbing another");
                right = chopsticks.take();
                right.take();
                System.out.println(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
                chopsticks.add(left);
                chopsticks.add(right);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }

    public String toString() {
        return "Philosopher " + id;
    }
} ///:~
