//: generics/Fill2.java
// Using adapters to simulate latent typing.
// {main: Fill2Test}
package com.example.doun.chapter15generics;

//import generics.Pet.*;

import com.example.doun.chapter15generics.pets.*;

import java.util.*;

//import net.mindview.util.*;

//import static net.mindview.util.System.out.println.*;

interface Addable<T> {
    void add(T t);
}

public class Fill2 {
    // Classtoken version:
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++)
            try {
                addable.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    // Generator version:
    public static <T> void fill(Addable<T> addable,
                                Generator<T> generator, int size) {
        for (int i = 0; i < size; i++)
            addable.add(generator.next());
    }
}

// To adapt a base type, you must use composition.
// Make any Collection Addable using composition:
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;

    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }

    public void add(T item) {
        c.add(item);
    }
}

// A Helper to capture the type automatically:
class Adapter {
    public static <T>
    Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<T>(c);
    }
}
//
//// To adapt a specific type, you can use inheritance.
//// Make a SimpleQueue Addable using inheritance:
//class AddableSimpleQueue<T>
//        extends SimpleQueue<T> implements Addable<T> {
//    public void add(T item) {
//        super.add(item);
//    }
//}

class Fill2Test {
    public static void main(String[] args) {
//        // Adapt a Collection:
//        List<Pet> carrier = new ArrayList<Pet>();
//        Fill2.fill(
//                new AddableCollectionAdapter<Pet>(carrier),
//                Pet.class, 3);
//        // Helper method captures the type:
//        Fill2.fill(Adapter.collectionAdapter(carrier),
//                Latte.class, 2);
//        for (Pet c : carrier)
//            System.out.println(c);
//        System.out.println("----------------------");
//        // Use an adapted class:
//        AddableSimpleQueue<Pet> coffeeQueue =
//                new AddableSimpleQueue<Pet>();
//        Fill2.fill(coffeeQueue, Mocha.class, 4);
//        Fill2.fill(coffeeQueue, Latte.class, 1);
//        for (Pet c : coffeeQueue)
//            System.out.println(c);

        // Adapt a Collection:
        List<Pet> carrier = new ArrayList<Pet>();
        Fill2.fill( new AddableCollectionAdapter<Pet>(carrier),Pet.class, 3);
        // Helper method captures the type:
        Fill2.fill(Adapter.collectionAdapter(carrier),Cat.class, 2);
        for (Pet c : carrier)
            System.out.println(c);
        System.out.println("----------------------");
//        // Use an adapted class:
//        AddableSimpleQueue<Pet> coffeeQueue =
//                new AddableSimpleQueue<Pet>();
//        Fill2.fill(coffeeQueue, Mocha.class, 4);
//        Fill2.fill(coffeeQueue, Latte.class, 1);
//        for (Pet c : coffeeQueue)
//            System.out.println(c);
        
        
    }
} /* Output:
Pet 0
Pet 1
Pet 2
Latte 3
Latte 4
----------------------
Mocha 5
Mocha 6
Mocha 7
Mocha 8
Latte 9
*///:~
