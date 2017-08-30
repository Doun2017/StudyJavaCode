//: net/mindview/util/Tuple.java
// Tuple library using type argument inference.
package com.example.doun.chapter17containers.tuple;
//package net.mindview.util;

public class Tuple {
    public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
        return new TwoTuple<A, B>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
        return new ThreeTuple<A, B, C>(a, b, c);
    }

    public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) {
        return new FourTuple<A, B, C, D>(a, b, c, d);
    }

    public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
        return new FiveTuple<A, B, C, D, E>(a, b, c, d, e);
    }

    public static void main(String[] args) {
        FiveTuple<String, Integer, Boolean, Short, Long>
                t5_1 = Tuple.tuple("a", 1, false, (short) 2, 3L),
                t5_2 = Tuple.tuple("b", 2, true, (short) 3, 4L);
        System.out.println("t5_1 = " + t5_1);
        System.out.println("t5_2 = " + t5_2);
        System.out.println("t5_1.equals(t5_1) = " + t5_1.equals(t5_1));
        System.out.println("t5_1.equals(t5_2) = " + t5_1.equals(t5_2));
        System.out.println("t5_1.compareTo(t5_1) = " + t5_1.compareTo(t5_1));
        System.out.println("t5_1.compareTo(t5_2) = " + t5_1.compareTo(t5_2));
    }


} ///:~
