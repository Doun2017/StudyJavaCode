//: generics/coffee/Americano.java
package com.example.doun.chapter14rtti.coffee;

import com.example.doun.chapter14rtti.factory.Factory;

public class Americano extends Coffee {
    public static class MyFactory implements Factory<Americano> {
        public Americano create() {
            return new Americano();
        }
    }
} ///:~
