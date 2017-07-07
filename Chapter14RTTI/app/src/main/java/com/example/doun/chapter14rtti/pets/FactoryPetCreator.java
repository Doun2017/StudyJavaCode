//: typeinfo/pets/PetCreator.java
// Creates random sequences of Pets.
package com.example.doun.chapter14rtti.pets;

import android.support.annotation.NonNull;

import com.example.doun.chapter14rtti.factory.Factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FactoryPetCreator extends PetCreator {

    static List<Factory<? extends Pet>> petFactories =
            new ArrayList<Factory<? extends Pet>>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        petFactories.add(new Pet.MyFactory());
        petFactories.add(new Dog.MyFactory());
        petFactories.add(new Cat.MyFactory());
        petFactories.add(new Rodent.MyFactory());
        petFactories.add(new Mutt.MyFactory());
        petFactories.add(new Pug.MyFactory());
        petFactories.add(new EgyptianMau.MyFactory());
        petFactories.add(new Manx.MyFactory());
        petFactories.add(new Cymric.MyFactory());
        petFactories.add(new Rat.MyFactory());
        petFactories.add(new Mouse.MyFactory());
        petFactories.add(new Hamster.MyFactory());
    }

    public List<Class<? extends Pet>> types() {
        List<Class<? extends Pet>> allTypes = new ArrayList<>();
        for (Factory<? extends Pet> factory:petFactories){
            allTypes.add(factory.create().getClass());
        }
        return allTypes;
    }

    public static void main(String[] args) {
        List<Pet> allObjs = new ArrayList<>();
        for (Factory<? extends Pet> factory:petFactories){
            allObjs.add(factory.create());
        }
        System.out.println(allObjs);
    }
} /* Output:

[Pet, Dog, Cat, Rodent, Mutt, Pug, EgyptianMau, Manx, Cymric, Rat, Mouse, Hamster]

*///:~

