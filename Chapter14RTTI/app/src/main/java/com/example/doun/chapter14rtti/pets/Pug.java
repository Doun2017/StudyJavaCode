//: typeinfo/pets/Pug.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Pug extends Dog {

  public static class MyFactory implements Factory<Pug> {
    public Pug create() {
      return new Pug();
    }
  }
  public Pug(String name) { super(name); }
  public Pug() { super(); }
} ///:~
