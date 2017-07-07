//: typeinfo/pets/Cat.java
//package com.example.doun.chapter14rtti.pets;
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Cat extends Pet {

  public static class MyFactory implements Factory<Cat> {
    public Cat create() {
      return new Cat();
    }
  }
  public Cat(String name) { super(name); }
  public Cat() { super(); }
} ///:~
