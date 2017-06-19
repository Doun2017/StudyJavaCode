package com.example.doun.learnjava;
//: innerclasses/Sequence3.java
// Holds a Sequence3 of Objects.


import java.util.ArrayList;

public class Sequence3 {
    private ArrayList items;
    private int next = 0;
    public Sequence3() { items = new ArrayList(); }
    public void add(Object x) {
        items.add(x);
    }

    private class SequenceSelector implements Selector {
        private int i = 0;
        public boolean end() { return i == items.size(); }
        public Object current() { return items.get(i); }
        public void next() { if(i < items.size()) i++; }
    }
    public Selector selector() {
        return new SequenceSelector();
    }
//    public static void main(String[] args) {
//        Sequence3 Sequence3 = new Sequence3();
//        for(int i = 0; i < 10; i++)
//            Sequence3.add(Integer.toString(i));
//        Selector selector = Sequence3.selector();
//        while(!selector.end()) {
//            System.out.print(selector.current() + " ");
//            selector.next();
//        }
//    }
} /* Output:
0 1 2 3 4 5 6 7 8 9
*///:~
