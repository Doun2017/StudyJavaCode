package com.example.doun.chapter21concurrency;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Doun on 2017/9/5.
 */
class RunnerOne implements Runnable{

    @Override
    public synchronized void run(){
        try {
            System.out.println("RunnerOne wait.");
            wait();
            System.out.println("someone notified me.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class RunnerTwo implements Runnable{
    @Override
    public void run(){
        RunnerOne runnerOne = new RunnerOne();
        new Thread(runnerOne).start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("notify RunnerOne.");
        synchronized(runnerOne){
            runnerOne.notifyAll();
        }

    }
}

public class Exercise21 {

    public static void main(String[] args) throws Exception {
        new Executor(){
            @Override
            public void execute(@NonNull Runnable command) {
                command.run();
            }
        }.execute(new RunnerTwo());
    }

}
