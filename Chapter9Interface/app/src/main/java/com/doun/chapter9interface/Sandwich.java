//: polymorphism/Sandwich.java
package com.doun.chapter9interface;


import android.util.Log;

interface FastFood {
    void makeFastFood();
}


class Meal {
    Meal() {
        Log.i(this.getClass().getName(), "Meal()");
    }
}

class Bread {
    Bread() {
        Log.i(this.getClass().getName(), "Bread()");
    }
}

class Cheese {
    Cheese() {
        Log.i(this.getClass().getName(), "Cheese()");
    }
}

class Lettuce {
    Lettuce() {
        Log.i(this.getClass().getName(), "Lettuce()");
    }
}

class Lunch extends Meal {
    Lunch() {
        Log.i(this.getClass().getName(), "Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        Log.i(this.getClass().getName(), "PortableLunch()");
    }
}

public class Sandwich extends PortableLunch implements FastFood{
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public Sandwich() {
        Log.i(this.getClass().getName(), "Sandwich()");
    }

    public static void main(String[] args) {
        new Sandwich();
    }

    @Override
    public void makeFastFood() {
        new Sandwich();
    }
} /* Output:
Meal()
Lunch()
PortableLunch()
Bread()
Cheese()
Lettuce()
Sandwich()
*///:~
