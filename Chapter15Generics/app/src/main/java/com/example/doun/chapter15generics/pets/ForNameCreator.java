//: typeinfo/pets/ForNameCreator.java
package com.example.doun.chapter15generics.pets;
import java.util.*;

public class ForNameCreator extends PetCreator {
  private static List<Class<? extends Pet>> types =
    new ArrayList<Class<? extends Pet>>();
  // Types that you want to be randomly created:
  private static String[] typeNames = {
    "com.example.doun.chapter15generics.pets.Mutt",
    "com.example.doun.chapter15generics.pets.Pug",
    "com.example.doun.chapter15generics.pets.EgyptianMau",
    "com.example.doun.chapter15generics.pets.Manx",
    "com.example.doun.chapter15generics.pets.Cymric",
    "com.example.doun.chapter15generics.pets.Rat",
    "com.example.doun.chapter15generics.pets.Mouse",
    "com.example.doun.chapter15generics.pets.Hamster"
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
