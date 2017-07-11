//: net/mindview/util/Sets.java
package com.example.doun.chapter15generics;

//package net.mindview.util;

import java.util.*;
import java.lang.Enum;

public class Sets {
    protected static <T> Set<T> copy(Set<T> s) {
        if(s instanceof EnumSet)
            return ((EnumSet)s).clone();
        return new HashSet<T>(s);
    }

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = copy(a);
        result.addAll(b);
        return result;
    }

//    public static <E> EnumSet<E extends Enum<E>> union1(EnmuSet<E extends Enum<E>> a,
//                                                        EnmuSet<E extends Enum<E>> b) {
//        Set<E> result = new HashSet<E>(a);
//        result.addAll(b);
//        return result;
//    }

    public static <T>
    Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = copy(a);
        result.retainAll(b);
        return result;
    }

    // Subtract subset from superset:
    public static <T> Set<T>
    difference(Set<T> superset, Set<T> subset) {
        Set<T> result = copy(superset);
        result.removeAll(subset);
        return result;
    }

    // Reflexive--everything not in the intersection:
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
} ///:~
