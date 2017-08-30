//: net/mindview/util/TwoTuple.java
package com.example.doun.chapter17containers.tuple;

//package net.mindview.util;

public class TwoTuple<A, B> implements Comparable<TwoTuple<A, B>> {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public int hashCode() {
        int result = 17;
        if (first != null)
            result = result * 37 + first.hashCode();
        if (second != null)
            result = result * 37 + second.hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TwoTuple) {
            @SuppressWarnings("unchecked")
            TwoTuple<A, B> o = (TwoTuple<A, B>) obj;
            return (first == null ?
                    o.first == null : first.equals(o.first)) &&
                    (second == null ?
                            o.second == null : second.equals(o.second));
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public int compareTo(TwoTuple<A, B> o) {
        int res = ((Comparable<A>) first).compareTo(o.first);
        if (res != 0) return res;
        return ((Comparable<B>) second).compareTo(o.second);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

} ///:~
