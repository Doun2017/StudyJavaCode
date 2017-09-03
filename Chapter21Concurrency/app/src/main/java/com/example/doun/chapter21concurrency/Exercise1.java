package com.example.doun.chapter21concurrency;

/**
 * Created by Doun on 2017/9/3.
 */

class RunAndYield implements Runnable{

    private static int taskCount = 0;
    private final int id = taskCount++;

    public RunAndYield() {
        System.out.println(id+" created");

    }

    @Override
    public void run() {
        System.out.println(id+" yield one");
        Thread.yield();
        System.out.println(id+" yield two");
        Thread.yield();
        System.out.println(id+" yield three");
        Thread.yield();

        System.out.println(id+" end");

    }
}
public class Exercise1 {
    public static void main(String[] args) {
        for (int i=0;i<10; i++){
            new Thread(new RunAndYield()).start();
        }
    }
}
