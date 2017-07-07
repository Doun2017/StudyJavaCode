//: typeinfo/pets/Rat.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Rat extends Rodent {

  public static class MyFactory implements Factory<Rat> {
    public Rat create() {
      return new Rat();
    }
  }

  public Rat(String name) { super(name); }
  public Rat() { super(); }
} ///:~
