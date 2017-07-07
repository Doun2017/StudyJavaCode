//: typeinfo/pets/Manx.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Manx extends Cat {

  public static class MyFactory implements Factory<Manx> {
    public Manx create() {
      return new Manx();
    }
  }

  public Manx(String name) { super(name); }
  public Manx() { super(); }
} ///:~
