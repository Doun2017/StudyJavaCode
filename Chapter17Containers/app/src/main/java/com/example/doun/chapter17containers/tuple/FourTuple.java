//: net/mindview/util/FourTuple.java
package com.example.doun.chapter17containers.tuple;
//package net.mindview.util;

public class FourTuple<A, B, C, D> implements Comparable<FourTuple<A,B,C,D>> {

    private final ThreeTuple<A,B,C> tuple;
    private final D fourth;
    public FourTuple(A a, B b, C c, D d) {
        tuple = new ThreeTuple<A,B,C>(a, b, c);
        fourth = d;
    }
    public String toString() {
        return "(" + tuple.getFirst() + ", " +
                tuple.getSecond() + ", " + tuple.getThird() +
                ", " + fourth + ")";
    }
    public int hashCode() {
        int result = tuple.hashCode();
        if(fourth != null)
            result = result * 37 + fourth.hashCode();
        return result;
    }
    public boolean equals(Object obj) {
        if(obj instanceof FourTuple) {
            @SuppressWarnings("unchecked")
            FourTuple<A,B,C,D> o = (FourTuple<A,B,C,D>)obj;
            return (fourth == null ?
                    o.fourth == null : fourth.equals(o.fourth)) &&
                    tuple.equals(o.tuple);
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public int compareTo(FourTuple<A,B,C,D> o) {
        int res = tuple.compareTo(o.tuple);
        if(res != 0) return res;
        return ((Comparable<D>)fourth).compareTo(o.fourth);
    }
    public A getFirst() { return tuple.getFirst(); }
    public B getSecond() { return tuple.getSecond(); }
    public C getThird() { return tuple.getThird(); }
    public D getFourth() { return fourth; }
                    
} ///:~
