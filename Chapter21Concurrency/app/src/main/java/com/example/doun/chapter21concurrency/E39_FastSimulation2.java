package com.example.doun.chapter21concurrency;

/**
 * Created by Doun on 2017/9/10.
 */
//: concurrency/E39_FastSimulation2.java
// {RunByHand}
/******************** Exercise 39 ************************
 * Does FastSimulation.java make reasonable assumptions?
 * Change the array to ordinary ints instead of
 * AtomicInteger and use Lock mutexes. Compare
 * performance between the two versions of the program.
 *********************************************************/

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.*;

public class E39_FastSimulation2 {
    static final int N_ELEMENTS = 10000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    // Variant 1 (optimistic locking):
    static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
    // Variant 2 (explicit locking):
    static final int[][] grid = new int[N_ELEMENTS][N_GENES];
    static final ReentrantLock[] lock = new ReentrantLock[N_ELEMENTS];
    // Counts the number of evolutions using 'variant 1':
    static final AtomicInteger counter1 = new AtomicInteger();
    // Counts the number of evolutions using 'variant 2':
    static final AtomicInteger counter2 = new AtomicInteger();
    static Random rand = new Random(47);

    static class Evolver1 implements Runnable {
        public void run() {
            while (!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                for (int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if (previous < 0) previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if (next >= N_ELEMENTS) next = 0;
                    int oldvalue = GRID[element][i].get();
                    int newvalue = oldvalue + GRID[previous][i].get() + GRID[next][i].get();
                    newvalue /= 3;
                    if (!GRID[element][i].compareAndSet(oldvalue, newvalue)) {
                        // Some backup action...
                    }
                }
                counter1.incrementAndGet();
            }
        }
    }

    static class Evolver2 implements Runnable {
        public void run() {
            while (!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                // Lock the whole row:
                lock[element].lock();
                try {
                    for (int i = 0; i < N_GENES; i++) {
                        int previous = element - 1;
                        if (previous < 0) previous = N_ELEMENTS - 1;
                        int next = element + 1;
                        if (next >= N_ELEMENTS) next = 0;
                        int newvalue = grid[element][i] + grid[previous][i] + grid[next][i];
                        newvalue /= 3;
                        grid[element][i] = newvalue;
                    }
                } finally {
                    lock[element].unlock();
                }
                counter2.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < N_ELEMENTS; i++)
            for (int j = 0; j < N_GENES; j++)
                GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
        for (int i = 0; i < N_ELEMENTS; i++)
            for (int j = 0; j < N_GENES; j++)
                grid[i][j] = rand.nextInt(1000);
        for (int i = 0; i < N_ELEMENTS; i++)
            lock[i] = new ReentrantLock();
        for (int i = 0; i < N_EVOLVERS; i++) {
            exec.execute(new Evolver1());
            exec.execute(new Evolver2());
        }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
        System.out.println("Variant 1: " + counter1.get());
        System.out.println("Variant 2: " + counter2.get());
    }
} /* (Execute to see output) *///:~
//Variant 1: 8658394
//Variant 2: 8217532

//Variant 1: 10073369
//Variant 2: 5526335

//Variant 1: 9329955
//Variant 2: 7160194


//    Experiment with different locking schemes; for example, lock something smaller
//        than the whole row. Risk optimistic locking only if the cost of a failure is low.
//        Otherwise, you lose the advantage gained by avoiding explicit locks in the first
//        place.