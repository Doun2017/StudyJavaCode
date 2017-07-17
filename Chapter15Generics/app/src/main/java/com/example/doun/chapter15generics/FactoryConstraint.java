//: generics/FactoryConstraint.java
package com.example.doun.chapter15generics;

interface FactoryI<T, O> {
    T create(O o);
}

class Foo2<T, O> {
    private T x;
    private O o;

    public <F extends FactoryI<T, O>> Foo2(F factory) {
        x = factory.create(o);
    }
    // ...
}

class IntegerFactory implements FactoryI<Integer, Integer> {
    public Integer create(Integer i) {
        return new Integer(i);
    }
}

class Widget {
    String name;
    Widget(String name){this.name = name;}
    public static class Factory implements FactoryI<Widget, String> {
        public Widget create(String name) {
            return new Widget(name);
        }
    }
}

public class FactoryConstraint {
    public static void main(String[] args) {
        new Foo2<Integer, Integer>(new IntegerFactory());
        new Foo2<Widget, String>(new Widget.Factory());
    }
} ///:~
