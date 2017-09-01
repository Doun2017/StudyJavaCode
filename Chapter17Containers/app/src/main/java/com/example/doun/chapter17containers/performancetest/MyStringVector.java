package com.example.doun.chapter17containers.performancetest;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Doun on 2017/9/1.
 */

public class MyStringVector {
    private String[] vector;
    private int size = 0;
    private int maxsize = 0;

    public MyStringVector() {
        maxsize = 10;
        vector = new String[maxsize];
    }

    public void add(String str) {
        if (size >= maxsize) {
            maxsize += 10;
            String[] newVector = Arrays.copyOf(vector, maxsize);
            vector = newVector;
        }
        vector[size] = str;
        size++;
    }

    public String get(int index) {
        if (index > size - 1)
            return null;
        else
            return vector[index];
    }

    public void set(int index, String str) {
        if (index > size - 1)
            return;
        else
            vector[index] = str;
    }


    public void insert(int index, String str) {
        if (index > size - 1)
            return;
        while (index < size) {
            String oldStr = vector[index];
            vector[index] = str;
            str = oldStr;
            index++;
        }
        add(str);
    }

    public void remove(int index) {
        if (index > size - 1)
            return;
        vector[index] = null;
        while (index + 1 < size - 1) {
            vector[index] = vector[index + 1];
            index++;
        }
        vector[index + 1] = null;
    }

    static final int LOOPS = 10000;
    static List<Test<List<String>>> alTests = new ArrayList<Test<List<String>>>();
    static List<Test<MyStringVector>> scTests = new ArrayList<Test<MyStringVector>>();

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
        scTests.add(new Test<MyStringVector>("addget") {
            int test(MyStringVector sc, TestParam tp) {
                for (int i = 0; i < LOOPS; i++) {
                    sc.add(Integer.toString(i));
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
        scTests.add(new Test<MyStringVector>("insertremove") {
            int test(MyStringVector sc, TestParam tp) {
                for (int i = 0; i < LOOPS; i++) {
                    sc.add(Integer.toString(i));
                }
                for (int i = 0; i < LOOPS; i++) {
                    sc.remove(199);
                    sc.insert(5, "123"); // Minimize random-access cost
                }
                return LOOPS;
            }
        });
    }

    public static void main(String[] args) {
        // Parameters are also hard-coded inside tests.
        Tester.defaultParams = TestParam.array(LOOPS, 1);
        Tester.run(new ArrayList<String>(LOOPS), alTests);
        Tester.run(new MyStringVector(), scTests);

    }
}