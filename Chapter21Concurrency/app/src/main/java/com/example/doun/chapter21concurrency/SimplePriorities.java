//: concurrency/SimplePriorities.java
// Shows the use of thread priorities.
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;

class MyPriorityThreadFactory implements ThreadFactory {
    private final int priority;
    public MyPriorityThreadFactory(int priority) {
        this.priority = priority;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(priority);
        return t;
    }
}

public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization
//    private int priority;

    public SimplePriorities() {
//        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
//        Thread.currentThread().setPriority(priority);
        while (true) {
            // An expensive, interruptable operation:
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();//exec默认执行的线程都是优先级5的线程
        MyPriorityThreadFactory priorityThreadFactoryH = new MyPriorityThreadFactory(3);
        MyPriorityThreadFactory priorityThreadFactoryN = new MyPriorityThreadFactory(Thread.NORM_PRIORITY);
        MyPriorityThreadFactory priorityThreadFactoryL = new MyPriorityThreadFactory(Thread.MIN_PRIORITY);
//        for (int i = 0; i < 5; i++)
//            exec.execute(priorityThreadFactoryL.newThread(new SimplePriorities()));
        exec.execute(priorityThreadFactoryH.newThread(new SimplePriorities()));//这里虽然也能接收Thread对象，但是是当着Runable使用的，所以这里的优先级被忽略掉了。
        exec.shutdown();
    }
} /* Output: (70% match)
Thread[pool-1-thread-6,10,main]: 5
Thread[pool-1-thread-6,10,main]: 4
Thread[pool-1-thread-6,10,main]: 3
Thread[pool-1-thread-6,10,main]: 2
Thread[pool-1-thread-6,10,main]: 1
Thread[pool-1-thread-3,1,main]: 5
Thread[pool-1-thread-2,1,main]: 5
Thread[pool-1-thread-1,1,main]: 5
Thread[pool-1-thread-5,1,main]: 5
Thread[pool-1-thread-4,1,main]: 5
...
*///:~
