package com.example.doun.chapter21concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Doun on 2017/9/3.
 */

class FibonacciSum implements Callable<Integer>{
    private int sum;

    private static int taskCount = 0;
    private final int id = taskCount++;
    public FibonacciSum(int sum) {
        this.sum = sum;
    }

    @Override
    public Integer call() throws Exception {
        Fibonacci gen = new Fibonacci();
        ArrayList<Integer> fibonacciArray = new ArrayList<>();
        for (int i = 0; i < sum; i++)
            fibonacciArray.add(gen.next());

        System.out.println(id+"thread:  "+fibonacciArray);
        Integer numberSum=0;
        for (Integer i:fibonacciArray)
            numberSum+=i;
        return numberSum;
    }
}
public class Exercise5 {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i=0;i<10; i++) {
            results.add(exe.submit(new FibonacciSum(i + 5)));
        }

        for (Future<Integer> fs : results)
            try {
                // get() blocks until completion:
                System.out.println("result of TaskWithResult " + fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exe.shutdown();
            }

    }
}