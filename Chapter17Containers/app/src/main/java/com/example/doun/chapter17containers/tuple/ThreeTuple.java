//: net/mindview/util/ThreeTuple.java
package com.example.doun.chapter17containers.tuple;

//ackage net.mindview.util;

public class ThreeTuple<A, B, C> implements Comparable<ThreeTuple<A,B,C>> {

    private final TwoTuple<A,B> tuple;
    private final C third;
    public ThreeTuple(A a, B b, C c) {
        tuple = new TwoTuple<A,B>(a, b);
        third = c;
    }
    public String toString() {
        return "(" + tuple.getFirst() + ", " +
                tuple.getSecond() + ", " + third +")";
    }
    public int hashCode() {
        int result = tuple.hashCode();
        if(third != null)
            result = result * 37 + third.hashCode();
        return result;
    }
    public boolean equals(Object obj) {
        if(obj instanceof ThreeTuple) {
            @SuppressWarnings("unchecked")
            ThreeTuple<A,B,C> o = (ThreeTuple<A,B,C>)obj;
            return (third == null ?
                    o.third == null : third.equals(o.third)) &&
                    tuple.equals(o.tuple);
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public int compareTo(ThreeTuple<A,B,C> o) {
        int res = tuple.compareTo(o.tuple);
        if(res != 0) return res;
        return ((Comparable<C>)third).compareTo(o.third);
    }
    public A getFirst() { return tuple.getFirst(); }
    public B getSecond() { return tuple.getSecond(); }
    public C getThird() { return third; }
} ///:~
