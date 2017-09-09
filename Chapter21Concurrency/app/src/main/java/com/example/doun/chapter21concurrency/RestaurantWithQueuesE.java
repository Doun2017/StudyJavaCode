//: concurrency/restaurant2/RestaurantWithQueuesE.java
// {Args: 5}
package com.example.doun.chapter21concurrency;

//package concurrency.restaurant2;
//import enumerated.menu.*;

import java.util.concurrent.*;
import java.util.*;
//import static net.mindview.util.System.out.println.*;
class TableE{
    private static int counter = 0;
    private final int id = counter++;

    public String toString() {
        return "Table " + id;
    }
}
// This is given to the waiter, who gives it to the ChefE:
class OrderTicketE { // (A data-transfer object)
    private static int counter = 0;
    private final int id = counter++;
    private final CustomerE CustomerE;
    private final WaitPersonE WaitPersonE;
    private final ArrayList<Food> food;

    public OrderTicketE(CustomerE cust, WaitPersonE wp, ArrayList<Food> f) {
        CustomerE = cust;
        WaitPersonE = wp;
        food = f;
    }

    public ArrayList<Food> item() {
        return food;
    }

    public CustomerE getCustomer() {
        return CustomerE;
    }

    public WaitPersonE getWaitPerson() {
        return WaitPersonE;
    }

    public String toString() {
        return "OrderTicketE: " + id + " item: " + food + " for: " + CustomerE + " served by: " + WaitPersonE;
    }
}

// This is what comes back from the ChefE:
class PlateE {
    private final OrderTicketE OrderTicketE;
    private final Food food;

    public PlateE(OrderTicketE ord, Food f) {
        OrderTicketE = ord;
        food = f;
    }

    public OrderTicketE getOrder() {
        return OrderTicketE;
    }

    public Food getFood() {
        return food;
    }

    public String toString() {
        return food.toString();
    }
}

class CustomerE implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final WaitPersonE WaitPersonE;
    private final TableE tableE;
    // Only one course at a time can be received:
    private SynchronousQueue<PlateE> placeSetting = new SynchronousQueue<>();

    public CustomerE(WaitPersonE w, TableE t) {
        WaitPersonE = w;
        tableE = t;
    }

    public void deliver(PlateE p) throws InterruptedException {
        // Only blocks if CustomerE is still
        // eating the previous course:
        placeSetting.put(p);
    }

    public void run() {
        System.out.println(this + "seating on " + tableE);
        ArrayList<Food> foods = new ArrayList<>();
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            foods.add(food);
        }
        try {
            WaitPersonE.placeOrder(this, foods);
            // Blocks until course has been delivered:
            for (Course course : Course.values()) {
                System.out.println(this + "eating " + placeSetting.take());
            }
        } catch (InterruptedException e) {
            System.out.println(this + "waiting for " + foods + " interrupted");
        }
        System.out.println(this + "finished meal, leaving");
    }

    public String toString() {
        return "CustomerE " + id + " ";
    }
}

class WaitPersonE implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final RestaurantE RestaurantE;
    BlockingQueue<PlateE> filledOrders = new LinkedBlockingQueue<>();

    public WaitPersonE(RestaurantE rest) {
        RestaurantE = rest;
    }

    public void placeOrder(CustomerE cust, ArrayList<Food> foods) {
        try {
            // Shouldn't actually block because this is
            // a LinkedBlockingQueue with no size limit:
            RestaurantE.orders.put(new OrderTicketE(cust, this, foods));
        } catch (InterruptedException e) {
            System.out.println(this + " placeOrder interrupted");
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until a course is ready
                PlateE PlateE = filledOrders.take();
                System.out.println(this + "received " + PlateE + " delivering to " + PlateE.getOrder().getCustomer());
                PlateE.getOrder().getCustomer().deliver(PlateE);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    public String toString() {
        return "WaitPersonE " + id + " ";
    }
}

class ChefE implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final RestaurantE RestaurantE;
    private static Random rand = new Random(47);

    public ChefE(RestaurantE rest) {
        RestaurantE = rest;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until an OrderTicketE appears:
                OrderTicketE OrderTicketE = RestaurantE.orders.take();
                ArrayList<Food> requestedFoods = OrderTicketE.item();
                for (Food food : requestedFoods){
                    // Time to prepare OrderTicketE:
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                    PlateE PlateE = new PlateE(OrderTicketE, food);
                    OrderTicketE.getWaitPerson().filledOrders.put(PlateE);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    public String toString() {
        return "ChefE " + id + " ";
    }
}

class RestaurantE implements Runnable {
    private List<WaitPersonE> waitPersons = new ArrayList<>();
    private List<TableE> tableEs = new ArrayList<>();
    private List<ChefE> chefs = new ArrayList<>();
    private ExecutorService exec;
    private static Random rand = new Random(47);
    BlockingQueue<OrderTicketE> orders = new LinkedBlockingQueue<>();

    public RestaurantE(ExecutorService e, int nWaitPersons, int nChefs) {
        exec = e;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPersonE WaitPersonE = new WaitPersonE(this);
            waitPersons.add(WaitPersonE);
            tableEs.add(new TableE());
            exec.execute(WaitPersonE);
        }
        for (int i = 0; i < nChefs; i++) {
            ChefE ChefE = new ChefE(this);
            chefs.add(ChefE);
            exec.execute(ChefE);
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // A new CustomerE arrives; assign a WaitPersonE:
                WaitPersonE wp = waitPersons.get(rand.nextInt(waitPersons.size()));
                TableE t = tableEs.get(rand.nextInt(tableEs.size()));
                CustomerE c = new CustomerE(wp, t);
                exec.execute(c);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("RestaurantE interrupted");
        }
        System.out.println("RestaurantE closing");
    }
}

public class RestaurantWithQueuesE {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        RestaurantE RestaurantE = new RestaurantE(exec, 5, 2);
        exec.execute(RestaurantE);
        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} /* Output: (Sample)
WaitPersonE 0 received SPRING_ROLLS delivering to CustomerE 1
CustomerE 1 eating SPRING_ROLLS
WaitPersonE 3 received SPRING_ROLLS delivering to CustomerE 0
CustomerE 0 eating SPRING_ROLLS
WaitPersonE 0 received BURRITO delivering to CustomerE 1
CustomerE 1 eating BURRITO
WaitPersonE 3 received SPRING_ROLLS delivering to CustomerE 2
CustomerE 2 eating SPRING_ROLLS
WaitPersonE 1 received SOUP delivering to CustomerE 3
CustomerE 3 eating SOUP
WaitPersonE 3 received VINDALOO delivering to CustomerE 0
CustomerE 0 eating VINDALOO
WaitPersonE 0 received FRUIT delivering to CustomerE 1
...
*///:~
