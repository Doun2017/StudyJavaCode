//: concurrency/ExchangerDemo34.java
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.*;
//import net.mindview.util.*;
/*
* Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了exchange()方法，
然后以线程安全的方式交换数据，之后线程A和B继续运行
*/
class ExchangerProducer34<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    ExchangerProducer34(Exchanger<List<T>> exchg, Generator<T> gen, List<T> holder) {
        exchanger = exchg;
        generator = gen;
        this.holder = holder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo34.size; i++)
                    holder.add(generator.next());
                // Exchange full for empty:
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
            // OK to terminate this way.
        }
    }
}

class ExchangerConsumer34<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    ExchangerConsumer34(Exchanger<List<T>> ex, List<T> holder) {
        exchanger = ex;
        this.holder = holder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for (T x : holder) {
                    value = x; // Fetch out value
                    holder.remove(x); // OK for CopyOnWriteArrayList
                }
            }
        } catch (InterruptedException e) {
            // OK to terminate this way.
        }
        System.out.println("Final value: " + value);
    }
}
class MyItem{
    static long item=0;

    public MyItem() {
        ++item;
    }

    @Override
    public String toString() {
        return item + " ";
    }
}
public class ExchangerDemo34 {
    static int size = 10;
    static int delay = 5; // Seconds

    public static void main(String[] args) throws Exception {
        if (args.length > 0)
            size = new Integer(args[0]);
        if (args.length > 1)
            delay = new Integer(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<MyItem>> xc = new Exchanger<>();
        List<MyItem> producerList = new CopyOnWriteArrayList<>(), consumerList = new CopyOnWriteArrayList<>();
        exec.execute(new ExchangerProducer34<>(xc, BasicGenerator.create(MyItem.class), producerList));
        exec.execute(new ExchangerConsumer34<>(xc, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
} /* Output: (Sample)
Final value: MyItem id: 29999
*///:~
