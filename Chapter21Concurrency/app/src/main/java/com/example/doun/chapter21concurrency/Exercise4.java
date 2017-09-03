package com.example.doun.chapter21concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Doun on 2017/9/3.
 */

public class Exercise4 {

    public static void main(String[] args) {
        //方式1
//        ExecutorService exec = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++)
//            exec.execute(new FibonacciArray(i+3));
//        exec.shutdown();
        //方式2
//        ExecutorService exec1 = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 5; i++)
//            exec1.execute(new FibonacciArray(i+3));
//        exec1.shutdown();
        /**

         1thread:  [1, 1, 2, 3]
         3thread:  [1, 1, 2, 3, 5, 8]
         0thread:  [1, 1, 2]
         2thread:  [1, 1, 2, 3, 5]
         4thread:  [1, 1, 2, 3, 5, 8, 13]

         */

        //方式3
        ExecutorService exec2 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            exec2.execute(new FibonacciArray(i+3));
        exec2.shutdown();

        /**

         0thread:  [1, 1, 2]
         1thread:  [1, 1, 2, 3]
         2thread:  [1, 1, 2, 3, 5]
         3thread:  [1, 1, 2, 3, 5, 8]
         4thread:  [1, 1, 2, 3, 5, 8, 13]

         */
    }
}
