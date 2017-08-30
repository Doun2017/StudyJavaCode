//: net/mindview/util/FiveTuple.java
package com.example.doun.chapter17containers.tuple;
//package net.mindview.util;

public class FiveTuple<A, B, C, D, E> implements Comparable<FiveTuple<A, B, C, D, E>> {
    private final FourTuple<A, B, C, D> tuple;
    private final E fifth;

    public FiveTuple(A a, B b, C c, D d, E e) {
        tuple = new FourTuple<A, B, C, D>(a, b, c, d);
        fifth = e;
    }

    public String toString() {
        return "(" + tuple.getFirst() + ", " +
                tuple.getSecond() + ", " + tuple.getThird() +
                ", " + tuple.getFourth() + ", " + fifth + ")";
    }

    public int hashCode() {
        int result = tuple.hashCode();
        if (fifth != null)
            result = result * 37 + fifth.hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FiveTuple) {
            @SuppressWarnings("unchecked")
            FiveTuple<A, B, C, D, E> o = (FiveTuple<A, B, C, D, E>) obj;
            return (fifth == null ?
                    o.fifth == null : fifth.equals(o.fifth)) &&
                    tuple.equals(o.tuple);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public int compareTo(FiveTuple<A, B, C, D, E> o) {
        int res = tuple.compareTo(o.tuple);
        if (res != 0) return res;
        return ((Comparable<E>) fifth).compareTo(o.fifth);
    }

    public A getFirst() {
        return tuple.getFirst();
    }

    public B getSecond() {
        return tuple.getSecond();
    }

    public C getThird() {
        return tuple.getThird();
    }

    public D getFourth() {
        return tuple.getFourth();
    }

    public E getFifth() {
        return fifth;
    }


} ///:~
