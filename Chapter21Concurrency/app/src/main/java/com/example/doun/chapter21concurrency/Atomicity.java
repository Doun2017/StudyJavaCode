//: concurrency/Atomicity.java
// {Exec: javap -c Atomicity}
package com.example.doun.chapter21concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Atomicity {
    int i;

    synchronized int f1() {
        return i++;
    }

    synchronized int f2() {
        return i += 3;
    }

    static Atomicity testItem = new Atomicity();
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++)
            exec.execute(new Runnable() {
                @Override
                public void run() {
//                    int ret = testItem.f2();
                    int ret = testItem.f1();
                    if (set.contains(ret))
                        System.out.print(ret + "出现过了 ");
                    else
                        set.add(ret);


//                    int ret = testItem.f1() + testItem.f2();
//                    if (ret%4 != 0)
//                        System.out.print(  ret + " ");
//                    System.out.println(testItem.f2() + " ");
                }
            });
        exec.shutdown();
    }

} /* Output: (Sample)
...
void f1();
  Code:
   0:        aload_0
   1:        dup
   2:        getfield        #2; //Field i:I
   5:        iconst_1
   6:        iadd
   7:        putfield        #2; //Field i:I
   10:        return

void f2();
  Code:
   0:        aload_0
   1:        dup
   2:        getfield        #2; //Field i:I
   5:        iconst_3
   6:        iadd
   7:        putfield        #2; //Field i:I
   10:        return
*///:~
/* 未加synchronized之前有时会有打印
f2:
1917出现过了
f1
463出现过了
* */