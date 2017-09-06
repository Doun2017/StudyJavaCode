//: concurrency/Restaurant27.java
// The producer-consumer approach to task cooperation.
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import static net.mindview.util.System.out.println.*;

class Meal27 {
    private final int orderNum;

    public Meal27(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal27 " + orderNum;
    }
}

class WaitPerson27 implements Runnable {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private Restaurant27 restaurant;

    public WaitPerson27(Restaurant27 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try{
                    while (restaurant.meal == null)
                        condition.await(); // ... for the chef to produce a meal
                }finally {
                    lock.unlock();
                }

                System.out.println("Waitperson got " + restaurant.meal);

                restaurant.chef.lock.lock();
                try{
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll(); // Ready for another
                }finally {
                    restaurant.chef.lock.unlock();
                }

            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson27 interrupted");
        }
    }
}

class Chef27 implements Runnable {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private Restaurant27 restaurant;
    private int count = 0;

    public Chef27(Restaurant27 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try{
                    while (restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }finally {
                    lock.unlock();
                }

                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return;
                }
                System.out.print("Order up! ");

                restaurant.waitPerson.lock.lock();
                try{
                    restaurant.meal = new Meal27(count);
                    restaurant.waitPerson.condition.signalAll();
                }finally {
                    restaurant.waitPerson.lock.unlock();
                }

                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef27 interrupted");
        }
    }
}

public class Restaurant27 {
    Meal27 meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson27 waitPerson = new WaitPerson27(this);
    Chef27 chef = new Chef27(this);

    public Restaurant27() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant27();
    }
} /* Output:
Order up! Waitperson got Meal27 1
Order up! Waitperson got Meal27 2
Order up! Waitperson got Meal27 3
Order up! Waitperson got Meal27 4
Order up! Waitperson got Meal27 5
Order up! Waitperson got Meal27 6
Order up! Waitperson got Meal27 7
Order up! Waitperson got Meal27 8
Order up! Waitperson got Meal27 9
Out of food, closing
WaitPerson27 interrupted
*///:~
