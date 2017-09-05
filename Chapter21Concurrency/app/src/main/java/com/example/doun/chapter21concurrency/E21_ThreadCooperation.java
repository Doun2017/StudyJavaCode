package com.example.doun.chapter21concurrency;

/**
 * Created by Doun on 2017/9/5.
 */

import java.util.concurrent.*;

//import static net.mindview.util.System.out.println.*;
class Coop1 implements Runnable {
    public Coop1() {
        System.out.println("Constructed Coop1");
    }

    public void run() {
        System.out.println("Coop1 going into wait");
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ignore) {
            }
        }
        System.out.println("Coop1 exited wait");
    }
}

class Coop2 implements Runnable {
    Runnable otherTask;

    public Coop2(Runnable otherTask) {
        this.otherTask = otherTask;
        System.out.println("Constructed Coop2");
    }

    public void run() {
        System.out.println("Coop2 pausing 5 seconds");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignore) {
        }
        System.out.println("Coop2 calling notifyAll");
        synchronized (otherTask) {
            otherTask.notifyAll();
        }
    }
}

public class E21_ThreadCooperation {
    public static void main(String args[]) throws Exception {
        Runnable coop1 = new Coop1(),
                coop2 = new Coop2(coop1);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(coop1);
        exec.execute(coop2);
        Thread.yield();
        exec.shutdown();
    }
} /* Output: (Sample)
Constructed Coop1
Constructed Coop2
Coop1 going into wait
Coop2 pausing 5 seconds
Coop2 calling notifyAll
Coop1 exited wait
*///:~
//    This is not a properly written concurrent program, although it runs correctly.
//        You should not synchronize tasks based on different timings. You will explore
//        more sophisticated solutions later in TIJ4.