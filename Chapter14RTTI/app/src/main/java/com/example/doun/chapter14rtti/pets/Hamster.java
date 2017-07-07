//: typeinfo/pets/Hamster.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Hamster extends Rodent {

  public static class MyFactory implements Factory<Hamster> {
    public Hamster create() {
      return new Hamster();
    }
  }

  public Hamster(String name) { super(name); }
  public Hamster() { super(); }
} ///:~
