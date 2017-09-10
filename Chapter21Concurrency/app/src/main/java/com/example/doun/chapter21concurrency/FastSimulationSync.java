//: concurrency/FastSimulationSync.java
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import static net.mindview.util.Print.*;

public class FastSimulationSync {
    static final int N_ELEMENTS = 100000;//基因数量， 基数
    static final int N_GENES = 30;//单个基因长度
    static final int N_EVOLVERS = 50;//进化者的数量
//    static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
    static final int[][] GRID = new int[N_ELEMENTS][N_GENES];
    static Random rand = new Random(47);
    static Lock unitLock = new ReentrantLock();


    static class Evolver implements Runnable {
        public void run() {
            while (!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                for (int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if (previous < 0) previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if (next >= N_ELEMENTS) next = 0;

                    unitLock.lock();

                    int oldvalue = GRID[element][i];
                    // Perform some kind of modeling calculation:
                    int newvalue = oldvalue + GRID[previous][i] + GRID[next][i];
                    newvalue /= 3; // Average the three values
                    GRID[element][i] = newvalue;

                    unitLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < N_ELEMENTS; i++)
            for (int j = 0; j < N_GENES; j++)
                GRID[i][j] = (rand.nextInt(1000));
        for (int i = 0; i < N_EVOLVERS; i++)
            exec.execute(new Evolver());
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
