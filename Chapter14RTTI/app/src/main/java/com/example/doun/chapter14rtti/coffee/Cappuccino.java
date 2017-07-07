//: generics/coffee/Cappuccino.java
package com.example.doun.chapter14rtti.coffee;

import com.example.doun.chapter14rtti.factory.Factory;

public class Cappuccino extends Coffee {
    public static class MyFactory implements Factory<Cappuccino> {
        public Cappuccino create() {
            return new Cappuccino();
        }
    }
} ///:~
