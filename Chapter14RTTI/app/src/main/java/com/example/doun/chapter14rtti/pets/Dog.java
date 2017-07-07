//: typeinfo/pets/Dog.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Dog extends Pet {

  public static class MyFactory implements Factory<Dog> {
    public Dog create() {
      return new Dog();
    }
  }
  public Dog(String name) { super(name); }
  public Dog() { super(); }
} ///:~
