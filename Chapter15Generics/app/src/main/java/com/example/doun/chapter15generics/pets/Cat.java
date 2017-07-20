//: typeinfo/pets/Cat.java
package com.example.doun.chapter15generics.pets;

public class Cat extends Pet {
    public Cat(String name) {
        super(name);
    }

    public Cat() {
        super();
    }

    public void speak() {
        System.out.println("Cat speak miao miao.");
    }
} ///:~
