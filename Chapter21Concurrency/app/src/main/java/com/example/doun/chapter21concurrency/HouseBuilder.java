//: concurrency/HouseBuilder.java
// A complex example of tasks working together.
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.*;
//import static net.mindview.util.System.out.println.*;

class House {
    private final int id;
    private boolean plumbing = false, concreteSlab = false, framing = false,
            laySteel = false, concreteForm = false, concreteFoundation = false;

    public House(int idn) {
        id = idn;
    }

    // Empty House object:
    public House() {
        id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addLaySteel() {
        laySteel = true;
    }
    public synchronized void addConcreteForm() {
        concreteForm = true;
    }
    public synchronized void addConcreteFoundation() {
        concreteFoundation = true;
    }
    public synchronized void addPlumbing() {
        plumbing = true;
    }

    public synchronized void addConcreteSlab() {
        concreteSlab = true;
    }

    public synchronized void addWheels() {
        framing = true;
    }

    public synchronized String toString() {
        return "House " + id + " [" +
                " plumbing: " + plumbing + " concreteSlab: " + concreteSlab + " framing: " + framing +
                " laySteel: " + laySteel + " concreteForm: " + concreteForm + " concreteFoundation: " + concreteFoundation +
                " ]";
    }
}

class HouseQueue extends LinkedBlockingQueue<House> {
}

class FootingsBuilder implements Runnable {
    private HouseQueue HouseQueue;
    private int counter = 0;

    public FootingsBuilder(HouseQueue cq) {
        HouseQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                // Make chassis:
                House c = new House(counter++);
                System.out.println("FootingsBuilder created " + c);
                // Insert into queue
                HouseQueue.put(c);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: FootingsBuilder");
        }
        System.out.println("FootingsBuilder off");
    }
}

class HouseAssembler implements Runnable {
    private HouseQueue chassisQueue, finishingQueue;
    private House House;
    private CyclicBarrier barrier = new CyclicBarrier(7);
    private WorkTeamPool workTeamPool;

    public HouseAssembler(HouseQueue cq, HouseQueue fq, WorkTeamPool rp) {
        chassisQueue = cq;
        finishingQueue = fq;
        workTeamPool = rp;
    }

    public House House() {
        return House;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until chassis is available:
                House = chassisQueue.take();
                // Hire workTeams to perform work:
                workTeamPool.hire(PlumbingWorkTeam.class, this);
                workTeamPool.hire(ConcreteSlabWorkTeam.class, this);
                workTeamPool.hire(FramingTeam.class, this);
                workTeamPool.hire(LaySteelWorkTeam.class, this);
                workTeamPool.hire(ConcreteFormWorkTeam.class, this);
                workTeamPool.hire(ConcreteFoundationWorkTeam.class, this);
                barrier.await(); // Until the workTeams finish
                // Put House into finishingQueue for further work
                finishingQueue.put(House);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting HouseAssembler via interrupt");
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        System.out.println("HouseAssembler off");
    }
}

class HouseReporter implements Runnable {
    private HouseQueue HouseQueue;

    public HouseReporter(HouseQueue cq) {
        HouseQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(HouseQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting HouseReporter via interrupt");
        }
        System.out.println("HouseReporter off");
    }
}

abstract class WorkTeam implements Runnable {
    private WorkTeamPool pool;

    public WorkTeam(WorkTeamPool p) {
        pool = p;
    }

    protected HouseAssembler HouseAssembler;

    public WorkTeam assignAssembler(HouseAssembler HouseAssembler) {
        this.HouseAssembler = HouseAssembler;
        return this;
    }

    private boolean engage = false;


    // The part of run() that's different for each workTeam:
    abstract protected void performService();

    public void run() {
        try {
            powerDown(); // Wait until needed
            while (!Thread.interrupted()) {
                performService();
                HouseAssembler.barrier().await(); // Synchronize
                // We're done with that job...
                powerDown();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        HouseAssembler = null; // Disconnect from the HouseAssembler
        // Put ourselves back in the available pool:
        pool.release(this);
        while (engage == false)  // Power down
            wait();
    }

    public String toString() {
        return getClass().getName();
    }
}

class LaySteelWorkTeam extends WorkTeam {
    public LaySteelWorkTeam(WorkTeamPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing LaySteel");
        HouseAssembler.House().addLaySteel();
    }
}
class ConcreteFormWorkTeam extends WorkTeam {
    public ConcreteFormWorkTeam(WorkTeamPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing ConcreteForm");
        HouseAssembler.House().addConcreteForm();
    }
}
class ConcreteFoundationWorkTeam extends WorkTeam {
    public ConcreteFoundationWorkTeam(WorkTeamPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing concreteFoundation");
        HouseAssembler.House().addConcreteFoundation();
    }
}
class PlumbingWorkTeam extends WorkTeam {
    public PlumbingWorkTeam(WorkTeamPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing plumbing");
        HouseAssembler.House().addPlumbing();
    }
}

class ConcreteSlabWorkTeam extends WorkTeam {
    public ConcreteSlabWorkTeam(WorkTeamPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing ConcreteSlab");
        HouseAssembler.House().addConcreteSlab();
    }
}

class FramingTeam extends WorkTeam {
    public FramingTeam(WorkTeamPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing Wheels");
        HouseAssembler.House().addWheels();
    }
}

class WorkTeamPool {
    // Quietly prevents identical entries:
    private Set<WorkTeam> pool = new HashSet<WorkTeam>();

    public synchronized void add(WorkTeam r) {
        pool.add(r);
        notifyAll();
    }

    public synchronized void hire(Class<? extends WorkTeam> workTeamType, HouseAssembler d) throws InterruptedException {
        for (WorkTeam r : pool)
            if (r.getClass().equals(workTeamType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage(); // Power it up to do the task
                return;
            }
        wait(); // None available
        hire(workTeamType, d); // Try again, recursively递归
    }

    public synchronized void release(WorkTeam r) {
        add(r);
    }
}

public class HouseBuilder {
    public static void main(String[] args) throws Exception {
        HouseQueue chassisQueue = new HouseQueue(),finishingQueue = new HouseQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        WorkTeamPool workTeamPool = new WorkTeamPool();
        exec.execute(new PlumbingWorkTeam(workTeamPool));
        exec.execute(new ConcreteSlabWorkTeam(workTeamPool));
        exec.execute(new FramingTeam(workTeamPool));
        exec.execute(new LaySteelWorkTeam(workTeamPool));
        exec.execute(new ConcreteFormWorkTeam(workTeamPool));
        exec.execute(new ConcreteFoundationWorkTeam(workTeamPool));
        exec.execute(new HouseAssembler(chassisQueue, finishingQueue, workTeamPool));
        exec.execute(new HouseReporter(finishingQueue));
        // Start everything running by producing chassis:
        exec.execute(new FootingsBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
