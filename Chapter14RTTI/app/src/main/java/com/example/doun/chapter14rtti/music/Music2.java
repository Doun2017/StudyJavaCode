//: polymorphism/music/Music2.java
// Overloading instead of upcasting.
package com.example.doun.chapter14rtti.music;

import java.util.ArrayList;
import java.util.List;

//有弦（乐器）
class Stringed extends Instrument {
    public void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }
}
//铜管乐器;
class Brass extends Instrument {
    public void play(Note n) {
        System.out.println("Brass.play() " + n);
    }
}

public class Music2 {
    public static void tune(Wind i) {
        i.play(Note.MIDDLE_C);
    }

    public static void tune(Stringed i) {
        i.play(Note.MIDDLE_C);
    }

    public static void tune(Brass i) {
        i.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
//        Wind flute = new Wind();
//        Stringed violin = new Stringed();
//        Brass frenchHorn = new Brass();
//        tune(flute); // No upcasting
//        tune(violin);
//        tune(frenchHorn);



        List<Instrument> instrumentations = new ArrayList<>();

        instrumentations.add(new Wind());
        instrumentations.add(new Brass());
        instrumentations.add(new Stringed());
        for (Instrument instrument : instrumentations){
            if (instrument instanceof Wind)
                ((Wind) instrument).clearSpitValve();
            instrument.play(Note.C_SHARP);
        }


    }
} /* Output:
Wind.play() MIDDLE_C
Stringed.play() MIDDLE_C
Brass.play() MIDDLE_C
*///:~
