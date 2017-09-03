package com.example.doun.chapter21concurrency;

import java.util.ArrayList;

/**
 * Created by Doun on 2017/9/3.
 */
class FibonacciArray implements Runnable{
    private int sum;

    private static int taskCount = 0;
    private final int id = taskCount++;
    public FibonacciArray(int sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        Fibonacci gen = new Fibonacci();
        ArrayList<Integer> fibonacciArray = new ArrayList<>();
        for (int i = 0; i < sum; i++)
            fibonacciArray.add(gen.next());

        System.out.println(id+"thread:  "+fibonacciArray);
    }
}
public class Exercise2 {
    public static void main(String[] args) {
        for (int i=0;i<10; i++){
            new Thread(new FibonacciArray(i+5)).start();
        }
    }
}
