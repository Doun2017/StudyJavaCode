package com.example.doun.chapter21concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Doun on 2017/9/3.
 */

public class Exercise3 {
    public static void main(String[] args) {
        //方式1
//        ExecutorService exec = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++)
//            exec.execute(new RunAndYield());
//        exec.shutdown();
        //方式2
//        ExecutorService exec1 = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 5; i++)
//            exec1.execute(new RunAndYield());
//        exec1.shutdown();
        //方式3
        ExecutorService exec2 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            exec2.execute(new RunAndYield());
        exec2.shutdown();
        /*
        *0 created
        1 created
        2 created
        3 created
        0 yield one
        4 created
        0 yield two
        0 yield three
        0 end
        1 yield one
        1 yield two
        1 yield three
        1 end
        2 yield one
        2 yield two
        2 yield three
        2 end
        3 yield one
        3 yield two
        3 yield three
        3 end
        4 yield one
        4 yield two
        4 yield three
        4 end
        * */

    }
}
