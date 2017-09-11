//: concurrency/WaxOMaticActiveObject/WaxOMaticActiveObject.java
// Basic task cooperation.
package com.example.doun.chapter21concurrency;

//package concurrency.WaxOMaticActiveObject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
//import static net.mindview.util.Print.*;

class CarAO {

    private ExecutorService ex = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);

    private boolean WaxOnAO = false;



    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public Future<Boolean> waxed() {
        return ex.submit(new Callable<Boolean>() {
            public Boolean call() {
                WaxOnAO = true; // Ready to buff
                pause(500);
                System.out.print("Wax On! ");
                return WaxOnAO;
            }
        });
    }

    public Future<Boolean> buffed() {
        return ex.submit(new Callable<Boolean>() {
            public Boolean call() {
                WaxOnAO = false; // Ready for another coat of wax
                pause(500);
                System.out.print("Wax Off! ");
                return WaxOnAO;
            }
        });
    }


    public void shutdown() {
        ex.shutdown();
    }

}

public class WaxOMaticActiveObject {
    public static void main(String[] args) throws Exception {
        CarAO CarAO = new CarAO();
        List<Future<?>> results = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 5; i++){
            results.add(CarAO.waxed());
            results.add(CarAO.buffed());
        }
        System.out.println("All asynch calls made");

        while (results.size() > 0) {
            for (Future<?> f : results)
                if (f.isDone()) {
                    try {
                        System.out.println(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
        }
        CarAO.shutdown();



//
//
//
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(new WaxOffAO(CarAO));
//        exec.execute(new WaxOnAO(CarAO));
//        TimeUnit.SECONDS.sleep(5); // Run for a while...
//        exec.shutdownNow(); // Interrupt all tasks
    }
} /* Output: (95% match)
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Exiting via interrupt
Ending Wax On task
Exiting via interrupt
Ending Wax Off task
*///:~
