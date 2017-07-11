//: generics/GenericMethods.java
package com.example.doun.chapter15generics;

public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public <T, A, B> void g(T x,A a,B b) {
        System.out.println("x---"+x.getClass().getName());
        System.out.println("a---"+a.getClass().getName());
        System.out.println("b---"+b.getClass().getName());
    }
    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);

    }
} /* Output:
java.lang.String
java.lang.Integer
java.lang.Double
java.lang.Float
java.lang.Character
GenericMethods
*///:~
