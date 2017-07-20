//: generics/CoffeeDecorator/Decoration.java
package com.example.doun.chapter15generics;

import java.util.*;

class CoffeeBasic {
    private String value;

    public void makeCoffee() {}
}

class CoffeeDecorator extends CoffeeBasic {
    protected CoffeeBasic CoffeeBasic;

    public CoffeeDecorator(CoffeeBasic CoffeeBasic) {
        this.CoffeeBasic = CoffeeBasic;
    }

    public void makeCoffee() {CoffeeBasic.makeCoffee();}
}

class MilkSteamed extends CoffeeDecorator {
    private final long timeStamp;

    public MilkSteamed(CoffeeBasic CoffeeBasic) {
        super(CoffeeBasic);
        timeStamp = new Date().getTime();
    }

    public long addMilk() {
        return timeStamp;
    }
}

class FoamSteamed extends CoffeeDecorator {
    public FoamSteamed(CoffeeBasic CoffeeBasic) {
        super(CoffeeBasic);
    }

    public long addFoam() {
        return 123;
    }
}

public class CoffeeDecoration {
    public static void main(String[] args) {
        MilkSteamed milkCoffee = new MilkSteamed(new CoffeeBasic());
        milkCoffee.addMilk();
        MilkSteamed milkCoffee2 = new MilkSteamed(new FoamSteamed(new CoffeeBasic()));
        milkCoffee2.addMilk();
//        milkCoffee2.addFoam;
        FoamSteamed foamCoffee = new FoamSteamed(new CoffeeBasic());
        foamCoffee.addFoam();
        FoamSteamed foamCoffee2 = new FoamSteamed(new MilkSteamed(new CoffeeBasic()));
    }
} ///:~
