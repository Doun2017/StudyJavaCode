//: typeinfo/pets/LiteralPetCreator.java
// Using class literals.
package com.example.doun.chapter15generics.pets;
import java.util.*;

public class LiteralPetCreator extends PetCreator {
  // No try block needed.
  @SuppressWarnings("unchecked")
  public static final List<Class<? extends Pet>> allTypes =
    Collections.unmodifiableList(Arrays.asList(
      Pet.class, Dog.class, Cat.class,  Rodent.class,
      Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
      Cymric.class, Rat.class, Mouse.class,Hamster.class));
  // Types for random creation:
  private static final List<Class<? extends Pet>> types =
    allTypes.subList(allTypes.indexOf(Mutt.class),
      allTypes.size());
  public List<Class<? extends Pet>> types() {
    return types;
  }	
  public static void main(String[] args) {
    System.out.println(types);
  }
} /* Output:
[class com.example.doun.chapter15generics.pets.Mutt, class com.example.doun.chapter15generics.pets.Pug, class com.example.doun.chapter15generics.pets.EgyptianMau, class com.example.doun.chapter15generics.pets.Manx, class com.example.doun.chapter15generics.pets.Cymric, class com.example.doun.chapter15generics.pets.Rat, class com.example.doun.chapter15generics.pets.Mouse, class com.example.doun.chapter15generics.pets.Hamster]
*///:~
