//: typeinfo/pets/Mutt.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Mutt extends Dog {

  public static class MyFactory implements Factory<Mutt> {
    public Mutt create() {
      return new Mutt();
    }
  }
  public Mutt(String name) { super(name); }
  public Mutt() { super(); }
} ///:~
