//: typeinfo/pets/ForNameCreator.java
package com.example.doun.chapter14rtti.pets;
import java.util.*;

public class ForNameCreator extends PetCreator {
  private static List<Class<? extends Pet>> types =
    new ArrayList<Class<? extends Pet>>();
  // Types that you want to be randomly created:
  private static String[] typeNames = {
    "com.example.doun.chapter14rtti.pets.Mutt",
    "com.example.doun.chapter14rtti.pets.Pug",
    "com.example.doun.chapter14rtti.pets.EgyptianMau",
    "com.example.doun.chapter14rtti.pets.Manx",
    "com.example.doun.chapter14rtti.pets.Cymric",
    "com.example.doun.chapter14rtti.pets.Rat",
    "com.example.doun.chapter14rtti.pets.Mouse",
    "com.example.doun.chapter14rtti.pets.Gerbil",
    "com.example.doun.chapter14rtti.pets.Hamster"
  };	
  @SuppressWarnings("unchecked")
  private static void loader() {
    try {
      for(String name : typeNames)
        types.add(
          (Class<? extends Pet>)Class.forName(name));
    } catch(ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  static { loader(); }
  public List<Class<? extends Pet>> types() {return types;}
} ///:~
