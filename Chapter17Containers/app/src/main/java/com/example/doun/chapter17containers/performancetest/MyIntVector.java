package com.example.doun.chapter17containers.performancetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Doun on 2017/9/1.
 */

public class MyIntVector {
    private int[] vector;
    private int size = 0;
    private int maxsize = 0;

    public MyIntVector() {
        maxsize = 10;
        vector = new int[maxsize];
    }

    public void add(int str) {
        if (size >= maxsize) {
            maxsize += 10;
            int[] newVector = Arrays.copyOf(vector, maxsize);
            vector = newVector;
        }
        vector[size] = str;
        size++;
    }

    public int get(int index) {
        if (index > size - 1)
            return 0;
        else
            return vector[index];
    }

    public void set(int index, int str) {
        if (index > size - 1)
            return;
        else
            vector[index] = str;
    }


    public void insert(int index, int str) {
        if (index > size - 1)
            return;
        while (index < size) {
            int oldStr = vector[index];
            vector[index] = str;
            str = oldStr;
            index++;
        }
        add(str);
    }

    public void remove(int index) {
        if (index > size - 1)
            return;
        vector[index] = 0;
        while (index + 1 < size - 1) {
            vector[index] = vector[index + 1];
            index++;
        }
        vector[index + 1] = 0;
    }

    static final int LOOPS = 10000;
    static List<Test<List<String>>> alTests = new ArrayList<Test<List<String>>>();
    static List<Test<MyIntVector>> scTests = new ArrayList<Test<MyIntVector>>();

    static {
        alTests.add(new Test<List<String>>("addget") {
            int test(List<String> list, TestParam tp) {
                for (int i = 0; i < LOOPS; i++) {
                    list.add(Integer.toString(i));
                    list.get(i);
                }
                return LOOPS;
            }
        });
        scTests.add(new Test<MyIntVector>("addget") {
            int test(MyIntVector sc, TestParam tp) {
                for (int i = 0; i < LOOPS; i++) {
                    sc.add(i);
                    sc.get(i);
                }
                return LOOPS;
            }
        });

        alTests.add(new Test<List<String>>("insertremove") {
            int test(List<String> list, TestParam tp) {
                for (int i = 0; i < LOOPS; i++) {
                    list.add(Integer.toString(i));
                }
                for (int i = 0; i < LOOPS; i++) {
                    list.remove(199);
                    list.add(5, "123"); // Minimize random-access cost
                }
                return LOOPS;
            }
        });
        scTests.add(new Test<MyIntVector>("insertremove") {
            int test(MyIntVector sc, TestParam tp) {
                for (int i = 0; i < LOOPS; i++) {
                    sc.add(i);
                }
                for (int i = 0; i < LOOPS; i++) {
                    sc.remove(199);
                    sc.insert(5, 23); // Minimize random-access cost
                }
                return LOOPS;
            }
        });
    }

    public static void main(String[] args) {
        // Parameters are also hard-coded inside tests.
        Tester.defaultParams = TestParam.array(LOOPS, 1);
        Tester.run(new ArrayList<String>(LOOPS), alTests);
        Tester.run(new MyIntVector(), scTests);

    }
}
/*
* ----- ArrayList -----
 size  addgetinsertremove
10000    3760   21452
---- MyIntVector ----
 size  addgetinsertremove
10000    1406   35403
*/