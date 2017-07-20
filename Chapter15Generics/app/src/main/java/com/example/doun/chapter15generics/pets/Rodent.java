//: typeinfo/pets/Rodent.java
package com.example.doun.chapter15generics.pets;

public class Rodent extends Pet {
    public Rodent(String name) {
        super(name);
    }

    public Rodent() {
        super();
    }

    public void speak() {
        System.out.println("Rodent speak zhi zhi .");
    }
} ///:~
