//: polymorphism/Frog.java
// Cleanup and inheritance.
package com.wsn.chapter12exception;

import android.util.Log;

class Characteristic {
    private String s;

    Characteristic(String s) {
        this.s = s;
        Log.e("Frog", "Creating Characteristic " + s);
    }

    protected void dispose() {
        Log.e("Frog", "disposing Characteristic " + s);
    }
}

class Description {
    private String s;

    Description(String s) {
        this.s = s;
        Log.e("Frog", "Creating Description " + s);
    }

    protected void dispose() {
        Log.e("Frog", "disposing Description " + s);
    }
}

class LivingCreature {
    private Characteristic p =
            new Characteristic("is alive");
    private Description t =
            new Description("Basic Living Creature");

    LivingCreature() {
        Log.e("Frog", "LivingCreature()");
    }

    protected void dispose() {
        Log.e("Frog", "LivingCreature dispose");
        t.dispose();
        p.dispose();
    }
}

class Animal extends LivingCreature {
    private Characteristic p =
            new Characteristic("has heart");
    private Description t =
            new Description("Animal not Vegetable");

    Animal() {
        Log.e("Frog", "Animal()");
    }

    protected void dispose() {
        Log.e("Frog", "Animal dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }
}

class Amphibian extends Animal {
    private Characteristic p =
            new Characteristic("can live in water");
    private Description t =
            new Description("Both water and land");

    Amphibian() {
        Log.e("Frog", "Amphibian()");
    }

    protected void dispose() {
        Log.e("Frog", "Amphibian dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }
}

public class Frog extends Amphibian {
    private Characteristic p = new Characteristic("Croaks");
    private Description t = new Description("Eats Bugs");

    public Frog() {
        Log.e("Frog", "Frog()");
    }

    protected void dispose() {
        Log.e("Frog", "Frog dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        Frog frog = new Frog();
        Log.e("Frog", "Bye!");
        frog.dispose();
    }
} /* Output:
Creating Characteristic is alive
Creating Description Basic Living Creature
LivingCreature()
Creating Characteristic has heart
Creating Description Animal not Vegetable
Animal()
Creating Characteristic can live in water
Creating Description Both water and land
Amphibian()
Creating Characteristic Croaks
Creating Description Eats Bugs
Frog()
Bye!
Frog dispose
disposing Description Eats Bugs
disposing Characteristic Croaks
Amphibian dispose
disposing Description Both water and land
disposing Characteristic can live in water
Animal dispose
disposing Description Animal not Vegetable
disposing Characteristic has heart
LivingCreature dispose
disposing Description Basic Living Creature
disposing Characteristic is alive
*///:~
