// generics/Holder3.java
// TIJ4 Chapter Generics, Exercise 1, page 621
// Use Holder3 with the com.example.doun.chapter15generics.pets library to show that a Holder3 that is
// specified to hold a base type can also hold a derived type.

package com.example.doun.chapter15generics;


import com.example.doun.chapter15generics.pets.*;

public class Holder3Objs<T> {
    private T a;
    private T b;
    private T c;

    public Holder3Objs(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    public T getC() {
        return c;
    }

    public void setC(T c) {
        this.c = c;
    }

    public static void main(String[] args) {
        Holder3Objs<Pet> h3 = new Holder3Objs<Pet>(new Pet(),new Pet(),new Pet());
        System.out.println(h3.getA());
        System.out.println(h3.getB());
        System.out.println(h3.getC());

    }
}