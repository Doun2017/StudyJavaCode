package com.example.doun.chapter21concurrency;

/**
 * Created by Doun on 2017/9/5.
 */

import java.util.concurrent.*;

public class E22_BusyWait {
    private static volatile boolean flag;
    private static int spins;

    public static void main(String[] args) throws Exception {
        Runnable r1 = new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                    flag = true;
                }
            }
        };
        Runnable r2 = new Runnable() {
            public void run() {
                for (; ; ) {
                    // The busy-wait loop
                    while (!flag && !Thread.currentThread().isInterrupted())
                        spins++;
                    System.out.println("Spun " + spins + " times");
                    spins = 0;
                    flag = false;
                    if (Thread.interrupted())
                        return;
                }
            }
        };
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r1);
        exec.execute(r2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }
} /* Output: (Sample)
Spun 53367 times
Spun 6862029 times
Spun 70570 times
Spun 1800294 times
Spun 3016012 times
Spun 2587723 times
Spun 2353137 times
Spun 2776382 times
Spun 3243885 times
Spun 3307477 times
Spun 3286525 times
Spun 3299882 times
Spun 3254685 times
Spun 3227281 times
Spun 3271655 times
Spun 3026290 times
Spun 3121522 times
Spun 2970803 times
Spun 3197368 times
Spun 3722021 times
Spun 4218599 times
Spun 3370463 times
Spun 2682228 times
Spun 3181608 times
Spun 3236782 times
Spun 3197264 times
Spun 3308013 times
Spun 3139611 times
Spun 3186413 times
Spun 3125828 times
Spun 2893148 times
Spun 3273041 times
Spun 3295419 times
Spun 3255247 times
Spun 3267457 times
Spun 3275099 times
Spun 3281934 times
Spun 3244790 times
Spun 3241901 times
Spun 3250314 times
Spun 3243260 times
Spun 3394222 times
Spun 3263500 times
Spun 3297276 times
Spun 3264272 times
Spun 3256665 times
Spun 3316411 times
Spun 3297350 times
Spun 3291031 times
Spun 3264929 times
Spun 3303795 times
Spun 3329465 times
Spun 3226166 times
Spun 3245121 times
Spun 3298176 times
Spun 3244283 times
Spun 3293792 times
Spun 3078174 times
Spun 3196162 times
Spun 3278712 times
Spun 3295314 times
Spun 3199674 times
Spun 3258059 times
Spun 3189726 times
Spun 3286176 times
Spun 3103101 times
Spun 3286590 times
Spun 3293754 times
Spun 3292578 times
Spun 3304328 times
Spun 3257858 times
Spun 3274291 times
Spun 3277463 times
Spun 3276287 times
Spun 3280674 times
Spun 3278831 times
Spun 3313727 times
Spun 3294615 times
Spun 3295179 times
Spun 3238894 times
Spun 3268321 times
Spun 3195552 times
Spun 3287549 times
Spun 3157832 times
Spun 3266832 times
Spun 3291503 times
Spun 3223449 times
Spun 3143975 times
Spun 3226635 times
Spun 3259538 times
Spun 3261657 times
Spun 3292424 times
Spun 3284062 times
Spun 3205507 times
Spun 3272940 times
Spun 3287335 times
Spun 3302956 times
Spun 809545 times
*///:~
//    flag facilitates communication betwee