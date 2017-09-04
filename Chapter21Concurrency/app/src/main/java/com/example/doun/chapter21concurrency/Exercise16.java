package com.example.doun.chapter21concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by power on 2017/9/4,004.
 */

public class Exercise16 {
    private Lock unitLock = new ReentrantLock();
    private Lock unitLock1 = new ReentrantLock();
    private Lock unitLock2 = new ReentrantLock();
    private Lock unitLock3 = new ReentrantLock();
    void f1(){
        unitLock1.lock();
        for(int i = 0; i < 5; i++) {
            System.out.println("f1()");
            Thread.yield();
        }
        unitLock1.unlock();
    }
    void f2(){
        unitLock2.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("f2()");
            Thread.yield();
        }
        unitLock2.unlock();
    }

    void f3() {
        unitLock.lock();

        for (int i = 0; i < 5; i++) {
            System.out.println("f3()");
            Thread.yield();
        }
        unitLock.unlock();
    }


    public static void main(String[] args) {
        final Exercise16 ds = new Exercise16();
        new Thread() {
            public void run() {
                ds.f1();
            }
        }.start();
        new Thread() {
            public void run() {
                ds.f2();
            }
        }.start();
        new Thread() {
            public void run() {
                ds.f3();
            }
        }.start();
    }
}
