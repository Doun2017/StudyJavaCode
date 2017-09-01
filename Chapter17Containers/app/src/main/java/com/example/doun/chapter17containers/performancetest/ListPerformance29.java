package com.example.doun.chapter17containers.performancetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by power on 2017/9/1,001.
 */

public class ListPerformance29 {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<String>>> tests = new ArrayList<Test<List<String>>>();
    static List<Test<LinkedList<String>>> qTests = new ArrayList<Test<LinkedList<String>>>();

    static {
        tests.add(new Test<List<String>>("add") {
            int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++) list.add(j+"");
                }
                return loops * listSize;
            }
        });
        tests.add(new Test<List<String>>("get") {
            int test(List<String> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++) list.get(rand.nextInt(listSize));
                return loops;
            }
        });
        tests.add(new Test<List<String>>("set") {
            int test(List<String> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++) list.set(rand.nextInt(listSize), 47+"");
                return loops;
            }
        });
        tests.add(new Test<List<String>>("iteradd") {
            int test(List<String> list, TestParam tp) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<String> it = list.listIterator(half);
                for (int i = 0; i < LOOPS; i++) it.add(47+"");
                return LOOPS;
            }
        });
        tests.add(new Test<List<String>>("insert") {
            int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++)
                    list.add(5, 47+""); // Minimize random-access cost
                return loops;
            }
        });
        tests.add(new Test<List<String>>("remove") {
            int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
//                    list.addAll(new CountingIntegerList(size));
                    list.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
                    while (list.size() > 5)
                        list.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });
        // Tests for queue behavior:
        qTests.add(new Test<LinkedList<String>>("addFirst") {
            int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addFirst(47+"");
                }
                return loops * size;
            }
        });
        qTests.add(new Test<LinkedList<String>>("addLast") {
            int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addLast(47+"");
                }
                return loops * size;
            }
        });
        qTests.add(
                new Test<LinkedList<String>>("rmFirst") {
                    int test(LinkedList<String> list, TestParam tp) {
                        int loops = tp.loops;
                        int size = tp.size;
                        for (int i = 0; i < loops; i++) {
                            list.clear();
                            list.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
                            while (list.size() > 0)
                                list.removeFirst();
                        }
                        return loops * size;
                    }
                });
        qTests.add(new Test<LinkedList<String>>("rmLast") {
            int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
                    while (list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    static class ListTester extends Tester<List<String>> {
        public ListTester(List<String> container,
                          List<Test<List<String>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<String> initialize(int size) {
            container.clear();
            container.addAll(Arrays.asList(Generated.array(String.class, new CountingGenerator.String(), size)));
            return container;
        }

        // Convenience method:
        public static void run(List<String> list,
                               List<Test<List<String>>> tests) {
            new ListPerformance29.ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        // Can only do these two tests on an array:
        Tester<List<String>> arrayTest = 
                new Tester<List<String>>(null, tests.subList(1, 3)) { 
                    /* This will be called before each test. It produces a non-resizeable array-backed list:*/
                    @Override
                    protected List<String> initialize(int size) {
                        String[] ia = Generated.array(String.class, new CountingGenerator.String(), size);
                        return Arrays.asList(ia);
                    }
                };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if (args.length > 0) Tester.defaultParams = TestParam.array(args);
        ListPerformance29.ListTester.run(new ArrayList<String>(), tests);
        ListPerformance29.ListTester.run(new LinkedList<String>(), tests);
        ListPerformance29.ListTester.run(new Vector<String>(), tests);
        Tester.fieldWidth = 12;
        Tester<LinkedList<String>> qTest = new Tester<LinkedList<String>>(new LinkedList<String>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }
}/* Output: (Sample)
--- Array as List ---
 size     get     set
   10      10      13
  100      11      14
 1000      11      10
10000       9      10
--------------------- ArrayList ---------------------
 size     add     get     set iteradd  insert  remove
   10     172      13      13      18     202     202
  100      57      11      12      14     204      80
 1000      28      11      12      43     131     117
10000      23      10      12     350    1123     476
--------------------- LinkedList ---------------------
 size     add     get     set iteradd  insert  remove
   10     138      23      23      13     101     145
  100      27      34      36      11      58      98
 1000      24     323     326       5      49      69
10000      25    4242    4168       5      44      88
----------------------- Vector -----------------------
 size     add     get     set iteradd  insert  remove
   10     106      12      12      21     220     157
  100      30      11      12      17     204     110
 1000      53      11      12      45     133     116
10000      20      13      13     356    1352     486
-------------------- Queue tests --------------------
 size    addFirst     addLast     rmFirst      rmLast
   10          38          37         139         137
  100          11          10          69          69
 1000           8           9          67          68
10000           8           7         104          76

Process finished with exit code 0
*///:~
