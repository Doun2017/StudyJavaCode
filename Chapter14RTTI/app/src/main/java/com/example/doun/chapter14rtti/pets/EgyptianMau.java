//: typeinfo/pets/EgyptianMau.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class EgyptianMau extends Cat {

  public static class MyFactory implements Factory<EgyptianMau> {
    public EgyptianMau create() {
      return new EgyptianMau();
    }
  }
  public EgyptianMau(String name) { super(name); }
  public EgyptianMau() { super(); }
} ///:~
