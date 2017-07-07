//: typeinfo/pets/Pets.java
// Facade to produce a default PetCreator.
package com.example.doun.chapter14rtti.pets;

import java.util.*;

public class Pets {
    public static final PetCreator creator =
            new LiteralPetCreator();

    public static final PetCreator factoryCreator =
            new FactoryPetCreator();


    public static Pet randomPet() {
        return factoryCreator.randomPet();
//        return creator.randomPet();
    }

    public static Pet[] createArray(int size) {
        return factoryCreator.createArray(size);
//        return creator.createArray(size);
    }

    public static ArrayList<Pet> arrayList(int size) {
        return factoryCreator.arrayList(size);
//        return creator.arrayList(size);
    }
} ///:~
