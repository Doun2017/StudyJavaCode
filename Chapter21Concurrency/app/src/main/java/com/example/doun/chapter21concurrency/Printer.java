package com.example.doun.chapter21concurrency;

/**
 * Created by Doun on 2017/9/3.
 */
class Printer implements Runnable {
    private static int taskCount;
    private final int id = taskCount++;
    Printer() {
        System.out.println("Printer started, ID = " + id);
    }
    public void run() {
        System.out.println("Stage 1..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 2..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 3..., ID = " + id);
        Thread.yield();
        System.out.println("Printer ended, ID = " + id);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
            new Thread(new Printer()).start();
    }
}
