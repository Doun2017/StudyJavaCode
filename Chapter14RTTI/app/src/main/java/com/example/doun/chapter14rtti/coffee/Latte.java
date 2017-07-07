//: generics/coffee/Latte.java
package com.example.doun.chapter14rtti.coffee;

import com.example.doun.chapter14rtti.factory.Factory;

public class Latte extends Coffee {
    public static class MyFactory implements Factory<Latte> {
        public Latte create() {
            return new Latte();
        }
    }
} ///:~
