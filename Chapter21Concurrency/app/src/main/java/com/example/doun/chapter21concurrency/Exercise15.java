package com.example.doun.chapter21concurrency;

/**
 * Created by power on 2017/9/4,004.
 */

//当同步对象是同一个时，打印是连续的，当同步对象不是同一个时，打印会乱
public class Exercise15 {
    private Object syncObject = new Object();
    private Object syncObject1 = new Object();
    private Object syncObject2 = new Object();
    private Object syncObject3 = new Object();

    void f1(){
        synchronized(syncObject) {
            for(int i = 0; i < 5; i++) {
                System.out.println("f1()");
                Thread.yield();
            }
        }
    }
    void f2(){
        synchronized(syncObject) {
            for(int i = 0; i < 5; i++) {
                System.out.println("f2()");
                Thread.yield();
            }
        }
    }
    void f3(){
        synchronized(syncObject) {
            for(int i = 0; i < 5; i++) {
                System.out.println("f3()");
                Thread.yield();
            }
        }
    }


    public static void main(String[] args) {
        final Exercise15 ds = new Exercise15();
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
