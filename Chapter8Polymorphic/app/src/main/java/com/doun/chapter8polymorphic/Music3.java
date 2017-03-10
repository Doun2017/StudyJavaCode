package com.doun.chapter8polymorphic;

import java.util.Random;

/**
 * Created by power on 2017/3/10,010.
 */
//: polymorphism/music3/Music3.java
// An extensible program.

class Instrument {
    String play(String note) { return ("Instrument.play() " + note); }
    public String toString() { return "Instrument"; }
    String adjust() { return ("Adjusting Instrument"); }
}

class Wind extends Instrument {
    String play(String note) { return ("Wind.play() " + note); }
    String what() { return "Wind"; }
    String adjust() { return ("Adjusting Wind"); }
}

class Percussion extends Instrument {
    String play(String note) { return ("Percussion.play() " + note); }
    String what() { return "Percussion"; }
    String adjust() { return ("Adjusting Percussion"); }
}

class Stringed extends Instrument {
    String play(String note) { return ("Stringed.play() " + note); }
    String what() { return "Stringed"; }
    String adjust() { return ("Adjusting Stringed"); }
}

class NewInstrument extends Instrument {
    String play(String note) { return ("NewInstrument.play() " + note); }
    String what() { return "NewInstrument"; }
    String adjust() { return ("Adjusting NewInstrument"); }
}

class Brass extends Wind {
    String play(String note) { return ("Brass.play() " + note); }
    String adjust() { return ("Adjusting Brass"); }
}

class Woodwind extends Wind {
    String play(String note) { return ("Woodwind.play() " + note); }
    String what() { return "Woodwind"; }
}



public class Music3 {
    // Doesn't care about type, so new types
    // added to the system still work right:
    public static String tune(Instrument i) {
        // ...
        return i.play("MIDDLE_C");
    }
    public static String tuneAll(Instrument[] e) {
        String ret = "";
        for(Instrument i : e)
            ret+=(tune(i)+"\n");
        return ret;
    }
    public static void main(String[] args) {
        // Upcasting during addition to the array:
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
} /* Output:
Wind.play() MIDDLE_C
Percussion.play() MIDDLE_C
Stringed.play() MIDDLE_C
Brass.play() MIDDLE_C
Woodwind.play() MIDDLE_C
*///:~




class RandomInstrumentGenerator {
    private Random rand = new Random(47);
    public Instrument next() {
        switch(rand.nextInt(4)) {
            default:
            case 0: return new Wind();
            case 1: return new Percussion();
            case 2: return new Stringed();
            case 3: return new NewInstrument();
        }
    }
} ///:~
