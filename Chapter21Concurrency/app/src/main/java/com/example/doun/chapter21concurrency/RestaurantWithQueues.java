//: concurrency/restaurant2/RestaurantWithQueues.java
// {Args: 5}
package com.example.doun.chapter21concurrency;

//package concurrency.restaurant2;
//import enumerated.menu.*;

import java.util.concurrent.*;
import java.util.*;
//import static net.mindview.util.System.out.println.*;

// This is given to the waiter, who gives it to the ChefQ:
class Order { // (A data-transfer object)
    private static int counter = 0;
    private final int id = counter++;
    private final CustomerQ CustomerQ;
    private final WaitPersonQ WaitPersonQ;
    private final Food food;

    public Order(CustomerQ cust, WaitPersonQ wp, Food f) {
        CustomerQ = cust;
        WaitPersonQ = wp;
        food = f;
    }

    public Food item() {
        return food;
    }

    public CustomerQ getCustomer() {
        return CustomerQ;
    }

    public WaitPersonQ getWaitPerson() {
        return WaitPersonQ;
    }

    public String toString() {
        return "Order: " + id + " item: " + food + " for: " + CustomerQ + " served by: " + WaitPersonQ;
    }
}

// This is what comes back from the ChefQ:
class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order ord, Food f) {
        order = ord;
        food = f;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    public String toString() {
        return food.toString();
    }
}

class CustomerQ implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final WaitPersonQ WaitPersonQ;
    // Only one course at a time can be received:
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public CustomerQ(WaitPersonQ w) {
        WaitPersonQ = w;
    }

    public void deliver(Plate p) throws InterruptedException {
        // Only blocks if CustomerQ is still
        // eating the previous course:
        placeSetting.put(p);
    }

    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            try {
                WaitPersonQ.placeOrder(this, food);
                // Blocks until course has been delivered:
                System.out.println(this + "eating " + placeSetting.take());
            } catch (InterruptedException e) {
                System.out.println(this + "waiting for " + course + " interrupted");
                break;
            }
        }
        System.out.println(this + "finished meal, leaving");
    }

    public String toString() {
        return "CustomerQ " + id + " ";
    }
}

class WaitPersonQ implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final RestaurantQ RestaurantQ;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();

    public WaitPersonQ(RestaurantQ rest) {
        RestaurantQ = rest;
    }

    public void placeOrder(CustomerQ cust, Food food) {
        try {
            // Shouldn't actually block because this is
            // a LinkedBlockingQueue with no size limit:
            RestaurantQ.orders.put(new Order(cust, this, food));
        } catch (InterruptedException e) {
            System.out.println(this + " placeOrder interrupted");
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until a course is ready
                Plate plate = filledOrders.take();
                System.out.println(this + "received " + plate + " delivering to " + plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    public String toString() {
        return "WaitPersonQ " + id + " ";
    }
}

class ChefQ implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final RestaurantQ RestaurantQ;
    private static Random rand = new Random(47);

    public ChefQ(RestaurantQ rest) {
        RestaurantQ = rest;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until an order appears:
                Order order = RestaurantQ.orders.take();
                Food requestedItem = order.item();
                // Time to prepare order:
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    public String toString() {
        return "ChefQ " + id + " ";
    }
}

class RestaurantQ implements Runnable {
    private List<WaitPersonQ> waitPersons = new ArrayList<>();
    private List<ChefQ> chefs = new ArrayList<>();
    private ExecutorService exec;
    private static Random rand = new Random(47);
    BlockingQueue<Order> orders = new LinkedBlockingQueue<>();

    public RestaurantQ(ExecutorService e, int nWaitPersons, int nChefs) {
        exec = e;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPersonQ WaitPersonQ = new WaitPersonQ(this);
            waitPersons.add(WaitPersonQ);
            exec.execute(WaitPersonQ);
        }
        for (int i = 0; i < nChefs; i++) {
            ChefQ ChefQ = new ChefQ(this);
            chefs.add(ChefQ);
            exec.execute(ChefQ);
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // A new CustomerQ arrives; assign a WaitPersonQ:
                WaitPersonQ wp = waitPersons.get(rand.nextInt(waitPersons.size()));
                CustomerQ c = new CustomerQ(wp);
                exec.execute(c);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("RestaurantQ interrupted");
        }
        System.out.println("RestaurantQ closing");
    }
}

public class RestaurantWithQueues {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        RestaurantQ RestaurantQ = new RestaurantQ(exec, 5, 2);
        exec.execute(RestaurantQ);
        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} /* Output: (Sample)
WaitPersonQ 0 received SPRING_ROLLS delivering to CustomerQ 1
CustomerQ 1 eating SPRING_ROLLS
WaitPersonQ 3 received SPRING_ROLLS delivering to CustomerQ 0
CustomerQ 0 eating SPRING_ROLLS
WaitPersonQ 0 received BURRITO delivering to CustomerQ 1
CustomerQ 1 eating BURRITO
WaitPersonQ 3 received SPRING_ROLLS delivering to CustomerQ 2
CustomerQ 2 eating SPRING_ROLLS
WaitPersonQ 1 received SOUP delivering to CustomerQ 3
CustomerQ 3 eating SOUP
WaitPersonQ 3 received VINDALOO delivering to CustomerQ 0
CustomerQ 0 eating VINDALOO
WaitPersonQ 0 received FRUIT delivering to CustomerQ 1
...
*///:~
