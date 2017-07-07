//: typeinfo/pets/Pet.java
package com.example.doun.chapter14rtti.pets;

import com.example.doun.chapter14rtti.factory.Factory;

public class Pet extends Individual {

    public static class MyFactory implements Factory<Pet> {
        public Pet create() {
            return new Pet();
        }
    }

    public Pet(String name) {
        super(name);
    }

    public Pet() {
        super();
    }
} ///:~
