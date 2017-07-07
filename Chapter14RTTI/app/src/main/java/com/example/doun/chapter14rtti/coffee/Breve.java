//: generics/coffee/Breve.java
package com.example.doun.chapter14rtti.coffee;

import com.example.doun.chapter14rtti.factory.Factory;

public class Breve extends Coffee {
    public static class MyFactory implements Factory<Breve> {
        public Breve create() {
            return new Breve();
        }
    }} ///:~
