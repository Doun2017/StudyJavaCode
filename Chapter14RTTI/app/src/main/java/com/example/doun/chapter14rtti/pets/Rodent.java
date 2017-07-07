//: typeinfo/pets/Rodent.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Rodent extends Pet {

  public static class MyFactory implements Factory<Rodent> {
    public Rodent create() {
      return new Rodent();
    }
  }
  public Rodent(String name) { super(name); }
  public Rodent() { super(); }
} ///:~
