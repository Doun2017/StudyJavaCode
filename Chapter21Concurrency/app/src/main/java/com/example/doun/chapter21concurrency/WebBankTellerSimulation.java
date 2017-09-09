//: concurrency/WebBankTellerSimulation.java
// Using queues and multithreading.
// {Args: 5}
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.*;

// Read-only objects don't require synchronization:
class WebCustomer {
    private final int serviceTime;

    public WebCustomer(int tm) {
        serviceTime = tm;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public String toString() {
        return "[" + serviceTime + "]";
    }
}

// Teach the WebCustomer line to display itself:
class WebCustomerLine extends ArrayBlockingQueue<WebCustomer> {
    public WebCustomerLine(int maxLineSize) {
        super(maxLineSize);
    }

    public String toString() {
        if (this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for (WebCustomer WebCustomer : this)
            result.append(WebCustomer);
        return result.toString();
    }
}

// Randomly add customers to a queue:
class WebCustomerGenerator implements Runnable {
    private WebCustomerLine customers;
    private static Random rand = new Random(47);

    public WebCustomerGenerator(WebCustomerLine cq) {
        customers = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.put(new WebCustomer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("WebCustomerGenerator interrupted");
        }
        System.out.println("WebCustomerGenerator terminating");
    }
}

class WebTeller implements Runnable, Comparable<WebTeller> {
    private static int counter = 0;
    private final int id = counter++;
    // Customers served during this shift:
    private int customersServed = 0;
    private WebCustomerLine customers;
    private boolean servingCustomerLine = true;

    public WebTeller(WebCustomerLine cq) {
        customers = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                WebCustomer WebCustomer = customers.take();
                TimeUnit.MILLISECONDS.sleep(WebCustomer.getServiceTime());
                synchronized (this) {
                    customersServed++;
                    while (!servingCustomerLine)
                        wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }

    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }

    public String toString() {
        return "WebTeller " + id + " ";
    }

    public String shortString() {
        return "T" + id;
    }

    // Used by priority queue:
    public synchronized int compareTo(WebTeller other) {
        return customersServed < other.customersServed ? -1 : (customersServed == other.customersServed ? 0 : 1);
    }
}

class WebTellerManager implements Runnable {
    private ExecutorService exec;
    private WebCustomerLine customers;
    private PriorityQueue<WebTeller> workingTellers = new PriorityQueue<>();
    private Queue<WebTeller> tellersDoingOtherThings = new LinkedList<>();
    private int adjustmentPeriod;
    private static Random rand = new Random(47);

    public WebTellerManager(ExecutorService e, WebCustomerLine customers, int adjustmentPeriod) {
        exec = e;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // Start with a single WebTeller:
        WebTeller WebTeller = new WebTeller(customers);
        exec.execute(WebTeller);
        workingTellers.add(WebTeller);
    }

    public void adjustTellerNumber() {
        // This is actually a control system. By adjusting
        // the numbers, you can reveal stability issues in
        // the control mechanism.
        // If line is too long, add another WebTeller:
        if (customers.size() / workingTellers.size() > 2) {
            // If tellers are on break or doing
            // another job, bring one back:
            if (tellersDoingOtherThings.size() > 0) {
                WebTeller WebTeller = tellersDoingOtherThings.remove();
                WebTeller.serveCustomerLine();
                workingTellers.offer(WebTeller);
                return;
            }
            // Else create (hire) a new WebTeller
            WebTeller WebTeller = new WebTeller(customers);
            exec.execute(WebTeller);
            workingTellers.add(WebTeller);
            return;
        }
        // If line is short enough, remove a WebTeller:
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2)
            reassignOneTeller();
        // If there is no line, we only need one WebTeller:
        if (customers.size() == 0)
            while (workingTellers.size() > 1)
                reassignOneTeller();
    }

    // Give a WebTeller a different job or a break:
    private void reassignOneTeller() {
        WebTeller WebTeller = workingTellers.poll();
        WebTeller.doSomethingElse();
        tellersDoingOtherThings.offer(WebTeller);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + " { ");
                for (WebTeller WebTeller : workingTellers)
                    System.out.print(WebTeller.shortString() + " ");
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public String toString() {
        return "WebTellerManager ";
    }
}

public class WebBankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // If line is too long, customers will leave:
        WebCustomerLine customers = new WebCustomerLine(MAX_LINE_SIZE);
        exec.execute(new WebCustomerGenerator(customers));
        // Manager will add and remove tellers as necessary:
        exec.execute(new WebTellerManager(exec, customers, ADJUSTMENT_PERIOD));
        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} /* Output: (Sample)
[429][200][207] { T0 T1 }
[861][258][140][322] { T0 T1 }
[575][342][804][826][896][984] { T0 T1 T2 }
[984][810][141][12][689][992][976][368][395][354] { T0 T1 T2 T3 }
WebTeller 2 interrupted
WebTeller 2 terminating
WebTeller 1 interrupted
WebTeller 1 terminating
WebTellerManager interrupted
WebTellerManager terminating
WebTeller 3 interrupted
WebTeller 3 terminating
WebTeller 0 interrupted
WebTeller 0 terminating
WebCustomerGenerator interrupted
WebCustomerGenerator terminating
*///:~
