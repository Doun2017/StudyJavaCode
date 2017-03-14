//: polymorphism/Sandwich.java
package com.doun.chapter8polymorphic;


import android.util.Log;

class Meal {
    Meal() {
        Log.i("Sandwich11", "Meal()");
    }
}

class Bread {
    Bread() {
        Log.i("Sandwich11", "Bread()");
    }
}

class Pickle {
    Pickle() {
        Log.i("Sandwich11", "Pickle()");
    }
}

class Cheese {
    Cheese() {
        Log.i("Sandwich11", "Cheese()");
    }
}

class Lettuce {
    Lettuce() {
        Log.i("Sandwich11", "Lettuce()");
    }
}

class Lunch extends Meal {
    Lunch() {
        Log.i("Sandwich11", "Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        Log.i("Sandwich11", "PortableLunch()");
    }
}

public class Sandwich11 extends PortableLunch {
    private Pickle p = new Pickle();
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public Sandwich11() {
        Log.i("Sandwich11", "Sandwich11()");
    }

    public static void main(String[] args) {
        new Sandwich11();
    }
}

/* Output:
Meal()
Lunch()
PortableLunch()
Bread()
Cheese()
Lettuce()
Sandwich()
*///:~
