//: concurrency/Restaurant26.java
// The producer-consumer approach to task cooperation.
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
//import static net.mindview.util.System.out.println.*;

class Meal26 {
    private final int orderNum;

    public Meal26(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal26 " + orderNum;
    }
}
class Table {
    private final int orderNum;

    public Table(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Table " + orderNum;
    }
}

class BusBoy implements Runnable {
    private Restaurant26 Restaurant26;

    public BusBoy(Restaurant26 r) {
        Restaurant26 = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (Restaurant26.table == null)
                        wait(); // ... for the WaitPerson26 to use a table
                }
                System.out.println("BusBoy cleaned " + Restaurant26.table);
                synchronized (Restaurant26.WaitPerson26) {
                    Restaurant26.table = null;
                    Restaurant26.WaitPerson26.notifyAll(); // Ready for another
                }
            }
        } catch (InterruptedException e) {
            System.out.println("BusBoy interrupted");
        }
    }
}

class WaitPerson26 implements Runnable {
    private Restaurant26 Restaurant26;
    private int count = 0;

    public WaitPerson26(Restaurant26 r) {
        Restaurant26 = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (Restaurant26.Meal26 == null)
                        wait(); // ... for the Chef26 to produce a Meal26
                }
                System.out.println("WaitPerson26 got " + Restaurant26.Meal26);
                synchronized (Restaurant26.Chef26) {
                    Restaurant26.Meal26 = null;
                    Restaurant26.Chef26.notifyAll(); // 通知chef可以有下一餐
                }

                synchronized (this) {
                    while (Restaurant26.table != null)
                        wait(); // ... for the table to be taken
                }
                synchronized (Restaurant26.busBoy) {
                    Restaurant26.table = new Table(++count);
                    Restaurant26.busBoy.notifyAll(); // 通知busBoy去clean一下table
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson26 interrupted");
        }
    }
}

class Chef26 implements Runnable {
    private Restaurant26 Restaurant26;
    private int count = 0;

    public Chef26(Restaurant26 r) {
        Restaurant26 = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (Restaurant26.Meal26 != null)
                        wait(); // ... for the Meal26 to be taken
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    Restaurant26.exec.shutdownNow();
                    return;
                }
                System.out.print("Order up! ");
                synchronized (Restaurant26.WaitPerson26) {
                    Restaurant26.Meal26 = new Meal26(count);
                    Restaurant26.WaitPerson26.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef26 interrupted");
        }
    }
}

public class Restaurant26 {
    Meal26 Meal26;
    Table table;
    ExecutorService exec = Executors.newCachedThreadPool();
    BusBoy busBoy = new BusBoy(this);
    WaitPerson26 WaitPerson26 = new WaitPerson26(this);
    Chef26 Chef26 = new Chef26(this);

    public Restaurant26() {
        exec.execute(Chef26);
        exec.execute(WaitPerson26);
        exec.execute(busBoy);
    }

    public static void main(String[] args) {
        new Restaurant26();
    }
} /* Output:

Order up! WaitPerson26 got Meal26 1
BusBoy cleaned Table 1
Order up! WaitPerson26 got Meal26 2
BusBoy cleaned Table 2
Order up! WaitPerson26 got Meal26 3
BusBoy cleaned Table 3
Order up! WaitPerson26 got Meal26 4
BusBoy cleaned Table 4
Order up! WaitPerson26 got Meal26 5
BusBoy cleaned Table 5
Order up! WaitPerson26 got Meal26 6
BusBoy cleaned Table 6
Order up! WaitPerson26 got Meal26 7
BusBoy cleaned Table 7
Order up! WaitPerson26 got Meal26 8
BusBoy cleaned Table 8
Order up! WaitPerson26 got Meal26 9
BusBoy cleaned Table 9
Out of food, closing
WaitPerson26 interrupted
BusBoy interrupted

*///:~
