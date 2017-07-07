//: typeinfo/pets/Mouse.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Mouse extends Rodent {

  public static class MyFactory implements Factory<Mouse> {
    public Mouse create() {
      return new Mouse();
    }
  }


  public Mouse(String name) { super(name); }
  public Mouse() { super(); }
} ///:~
