//: typeinfo/pets/Cymric.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Cymric extends Manx {

  public static class MyFactory implements Factory<Cymric> {
    public Cymric create() {
      return new Cymric();
    }
  }

  public Cymric(String name) { super(name); }
  public Cymric() { super(); }
} ///:~
