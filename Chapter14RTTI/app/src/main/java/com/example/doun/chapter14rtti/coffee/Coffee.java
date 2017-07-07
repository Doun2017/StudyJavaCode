//: generics/coffee/Coffee.java
package com.example.doun.chapter14rtti.coffee;

import com.example.doun.chapter14rtti.factory.Factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Coffee {

    private static Random rand = new Random(47);

    static List<Factory<? extends Coffee>> coffeeFactories =
            new ArrayList<Factory<? extends Coffee>>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        coffeeFactories.add(new Americano.MyFactory());
        coffeeFactories.add(new Breve.MyFactory());
        coffeeFactories.add(new Cappuccino.MyFactory());
        coffeeFactories.add(new Latte.MyFactory());
        coffeeFactories.add(new Mocha.MyFactory());
    }

    public static Coffee createRandom() {
        int n = rand.nextInt(coffeeFactories.size());
        return coffeeFactories.get(n).create();
    }

    private static long counter = 0;
    private final long id = counter++;

    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 15; i++)
            System.out.println(Coffee.createRandom());
    }

} /* Output:
Latte 0
Americano 1
Latte 2
Breve 3
Breve 4
Mocha 5
Latte 6
Americano 7
Cappuccino 8
Cappuccino 9
Latte 10
Latte 11
Breve 12
Mocha 13
Mocha 14
*///:~