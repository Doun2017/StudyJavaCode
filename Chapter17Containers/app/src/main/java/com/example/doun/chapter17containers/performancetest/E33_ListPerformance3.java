package com.example.doun.chapter17containers.performancetest;

/**
 * Created by Doun on 2017/9/1.
 */

import java.util.*;

//import net.mindview.util.*;
class FastTraversalLinkedList<T> extends AbstractList<T> {
    private class FlaggedArrayList {
        private FlaggedLinkedList companion;
        boolean changed = false;
        private ArrayList<T> list = new ArrayList<T>();

        public void addCompanion(FlaggedLinkedList other) {
            companion = other;
        }

        private void synchronize() {
            if (companion.changed) {
                list = new ArrayList<T>(companion.list);
                companion.changed = false;
            }
        }

        public T get(int index) {
            synchronize();
            return list.get(index);
        }

        public int size() {
            synchronize();
            return list.size();
        }

        public T remove(int index) {
            synchronize();
            changed = true;
            return list.remove(index);
        }

        public boolean remove(Object item) {
            synchronize();
            changed = true;
            return list.remove(item);
        }

        // Always broadcasted to the companion container, too.
        public void clear() {
            list.clear();
            changed = false;
        }
    }

    private class FlaggedLinkedList {
        private FlaggedArrayList companion;
        boolean changed = false;
        LinkedList<T> list = new LinkedList<T>();

        public void addCompanion(FlaggedArrayList other) {
            companion = other;
        }

        private void synchronize() {
            if (companion.changed) {
                list = new LinkedList<T>(companion.list);
                companion.changed = false;
            }
        }

        public void add(int index, T item) {
            synchronize();
            changed = true;
            list.add(index, item);
        }

        public boolean add(T item) {
            synchronize();
            changed = true;
            return list.add(item);
        }

        public Iterator<T> iterator() {
            synchronize();
            return list.iterator();
        }

        // Always broadcasted to the companion container, too.
        public void clear() {
            list.clear();
            changed = false;
        }
    }

    private FlaggedArrayList aList = new FlaggedArrayList();
    private FlaggedLinkedList lList = new FlaggedLinkedList();

    // Connect the two so they can synchronize:
    {
        aList.addCompanion(lList);
        lList.addCompanion(aList);
    }

    public int size() {
        return aList.size();
    }

    public T get(int arg) {
        return aList.get(arg);
    }

    public void add(int index, T item) {
        lList.add(index, item);
    }

    public boolean add(T item) {
        return lList.add(item);
    }

    // Through testing, we discovered that the ArrayList is
// actually much faster for removals than the LinkedList:
    public T remove(int index) {
        return aList.remove(index);
    }

    public boolean remove(Object item) {
        return aList.remove(item);
    }

    public Iterator<T> iterator() {
        return lList.iterator();
    }

    public void clear() {
        aList.clear();
        lList.clear();
    }
}

public class E33_ListPerformance3 {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests =
            new ArrayList<Test<List<Integer>>>();

    static {
        tests.add(new Test<List<Integer>>("iter") {
            int test(List<Integer> list, TestParam tp) {
                for (int i = 0; i < tp.loops; i++) {
                    Iterator<Integer> it = list.iterator();
                    while (it.hasNext())
                        it.next(); // Produces value
                }
                return tp.loops;
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
        tests.add(new Test<List<Integer>>("insert") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++)
                    list.add(5, 47); // Minimize random-access cost
                return loops;
            }
        });
        tests.add(new Test<List<Integer>>("remove_I ") {
            int test(List<Integer> list, TestParam tp) {
                int count = 0;
                for (int i = list.size() / 2; i < list.size();i++) {
                    ++count;
                    list.remove(i);
                }
                return count;
            }
        });
        tests.add(new Test<List<Integer>>("remove_O") {
            int test(List<Integer> list, TestParam tp) {
                int count = 0;
                for (int i = list.size() / 2; i < list.size();
                     i++) {
                    ++count;
                    list.remove(list.get(i));
                }
                return count;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container,List<Test<List<Integer>>> tests) {
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
        public static void run(List<Integer> list,List<Test<List<Integer>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        ListTester.run(new ArrayList<Integer>(), tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new FastTraversalLinkedList<Integer>(), tests);
    }
} ///:~
/*
----------------- ArrayList -----------------
 size    iter     get  insertremove_I remove_O
   10    1216      37    1518   16988   11099
  100    3941      39    1000     733   11769
 1000    2200      29    1304     610   16076
10000      11      31    3778    1272   14459
----------------- LinkedList -----------------
 size    iter     get  insertremove_I remove_O
   10    1223      40     316    6116    3058
  100     169      62     121     475    3683
 1000    1266     325      94     661   15970
10000   17240    3160     101    3270   21850
---------- FastTraversalLinkedList ----------
 size    iter     get  insertremove_I remove_O
   10    1086      32     167   27182    4530
  100     669      32     109     829     883
 1000    5830      31      92     887    1863
10000   60106      32      98    1167   16170
*/