package com.example.doun.chapter17containers.performancetest;

import com.example.doun.chapter17containers.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by power on 2017/9/1,001.
 */

public class ListPerformance30 {

    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();

    static {
        tests.add(new Test<List<Integer>>("sort") {
            int test(List<Integer> list, TestParam tp) {
                for(int i = 0; i < tp.loops; i++) {
                    list.clear();
//                    list.addAll(CollectionData.list( new RandomGenerator.Integer(), tp.size));
//                    list.addAll(new CountingIntegerList(tp.size));
                    list.addAll(Arrays.asList(Generated.array(Integer.class, new CountingGenerator.Integer(),  tp.size)));
                    Collections.sort(list);
                }
                return tp.loops;
            }
        });

        tests.add(new Test<List<Integer>>("add") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++)
                        list.add(j);
                }
                return loops * listSize;
            }
        });
        tests.add(new Test<List<Integer>>("get") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        // Convenience method:
        public static void run(List<Integer> list, List<Test<List<Integer>>> tests) {
            new ListPerformance30.ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        ListPerformance30.ListTester.run(new ArrayList<Integer>(), tests);
        ListPerformance30.ListTester.run(new LinkedList<Integer>(), tests);
    }
}
