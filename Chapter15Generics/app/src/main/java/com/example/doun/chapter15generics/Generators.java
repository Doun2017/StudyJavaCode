//: generics/Generators.java
// A utility to use with Generators.
package com.example.doun.chapter15generics;

import java.util.*;

public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> LinkedList<T> fill(LinkedList<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> ArrayList<T> fill(ArrayList<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> Queue<T> fill(Queue<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> Set<T> fill(Set<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
//    Collection<Coffee> coffee = fill(
//      new ArrayList<Coffee>(), new CoffeeGenerator(), 4);
//    for(Coffee c : coffee)
//      System.out.println(c);
        ArrayList<Integer> fnumbers = fill(
                new ArrayList<Integer>(), new Fibonacci(), 12);
        for (int i : fnumbers)
            System.out.print(i + ", ");
    }
} /* Output:
Americano 0
Latte 1
Americano 2
Mocha 3
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
*///:~
