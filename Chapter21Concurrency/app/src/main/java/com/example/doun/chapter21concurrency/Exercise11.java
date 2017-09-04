package com.example.doun.chapter21concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by power on 2017/9/4,004.
 */
class TestItem {
    private int a;
    private int b;
    private int c;
    private int d;

    synchronized public int addResultEven(){
        a++;
        b++;
        c++;
        d++;
        return a+b+c+d;
    }
}
//public class EvenChecker implements Runnable {
//    private IntGenerator generator;
//    private final int id;
//    public EvenChecker(IntGenerator g, int ident) {
//        generator = g;
//        id = ident;
//    }
//    public void run() {
//        while(!generator.isCanceled()) {
//            int val = generator.next();
//            if(val % 2 != 0) {
//                System.out.println(val + " not even!");
//                generator.cancel(); // Cancels all EvenCheckers
//            }
//        }
//    }
//    // Test any type of IntGenerator:
//    public static void test(IntGenerator gp, int count) {
//        System.out.println("Press Control-C to exit");
//        ExecutorService exec = Executors.newCachedThreadPool();
//        for(int i = 0; i < count; i++)
//            exec.execute(new EvenChecker(gp, i));
//        exec.shutdown();
//    }
//    // Default value for count:
//    public static void test(IntGenerator gp) {
//        test(gp, 10);
//    }
//}
public class Exercise11 {
    static TestItem testItem = new TestItem();

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 100; i++)
            exec.execute(new Runnable(){
                @Override
                public void run() {
                    System.out.print(testItem.addResultEven()+" ");
                }
            });
        exec.shutdown();
    }


}
/*未加synchronized之前有时会出现奇数
7 12 8 16 20 24 28 36 32 40 44 52 48 56 60 64 68 72 76 84 80 88 92 100 104 96 108 112 116 120 124 128 132 136 144 148
140 152 184 188 180 176 200 208 232 172 236 244 164 248 256 260 268 168 272 280 284 160 288 156 292 304 308 316 320 324
328 332 336 340 348 356 360 364 368 372 376 384 276 388 392 396 264 252 240 224 228 220 216 212 204 196 192 400 380 352 344 312 300 296
* */
/*
4 12 16 8 20 24 28 44 40 52 36 32 56 64 48 60 68 72 76 80 84 88 96 92 100 104 108 112 116 120 124 128 132 136 140 156
 144 176 188 196 148 204 200 224 192 180 228 184 172 256 164 264 168 160 152 280 288 276 272 268 300 308 316 260 252 248
  244 236 348 240 364 232 220 216 372 384 212 208 388 376 392 380 368 360 356 352 344 340 336 332 320 324 328 312 304 296 292 284 400 396
* */