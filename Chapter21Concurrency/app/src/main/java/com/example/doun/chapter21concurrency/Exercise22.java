package com.example.doun.chapter21concurrency;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Doun on 2017/9/5.
 */

public class Exercise22 {

    public static boolean flag = false;
    static class RunnerOne implements Runnable{

        @Override
        public synchronized void run(){
            System.out.println(System.currentTimeMillis());
            System.out.println("RunnerOne run.");

            try {
                TimeUnit.SECONDS.sleep(3);
                flag = true;
                System.out.println(System.currentTimeMillis());
                System.out.println("flag set to true.");

//                System.out.println("RunnerOne wait.");
//                wait();
//                System.out.println("someone notified me.");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class RunnerTwo implements Runnable{
        @Override
        public void run(){
            System.out.println(System.currentTimeMillis());
            System.out.println("RunnerTwo run.");

            RunnerOne runnerOne = new RunnerOne();
            new Thread(runnerOne).start();
            while (flag == false){
                try {
                    TimeUnit.MICROSECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
            flag = false;
            System.out.println(System.currentTimeMillis());
            System.out.println("flag set to false.");

//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            System.out.println("notify RunnerOne.");
//            synchronized(runnerOne){
//                runnerOne.notifyAll();
//            }

        }
    }

    public static void main(String[] args) throws Exception {
        new Executor(){
            @Override
            public void execute(@NonNull Runnable command) {
                command.run();
            }
        }.execute(new Exercise22.RunnerTwo());
    }

}
/*
1504618540477
RunnerTwo run.
1504618540489
RunnerOne run.
1504618543489
1504618543489
flag set to true.
flag set to false.

* */