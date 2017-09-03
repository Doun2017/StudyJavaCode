//: concurrency/SimpleThread.java
// Inheriting directly from the Thread class.
package com.example.doun.chapter21concurrency;

public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        // Store the thread name:
        super(Integer.toString(++threadCount));
        this.setDaemon(true);
        start();
    }

    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
    }

    public void run() {
        while (true) {
            //不加for循环会全部打印出来。
            for (int i=0; i<10; i++){
                double d = 432.333/335.3;//加上这一句就只能打印一部分了
//                d = d/44.44; //再加上这一句就什么也不打印了
            }
            System.out.print(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SimpleThread();
    }
} /* Output:
#1(5), #1(4), #1(3), #1(2), #1(1), #2(5), #2(4), #2(3), #2(2), #2(1), #3(5), #3(4), #3(3), #3(2), #3(1), #4(5), #4(4), #4(3), #4(2), #4(1), #5(5), #5(4), #5(3), #5(2), #5(1),
*///:~
