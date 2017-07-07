//: generics/coffee/Mocha.java
package com.example.doun.chapter14rtti.coffee;

import com.example.doun.chapter14rtti.factory.Factory;

public class Mocha extends Coffee {
    public static class MyFactory implements Factory<Mocha> {
        public Mocha create() {
            return new Mocha();
        }
    }
} ///:~
