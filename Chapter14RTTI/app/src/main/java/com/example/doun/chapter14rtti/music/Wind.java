//: polymorphism/music/Wind.java
package com.example.doun.chapter14rtti.music;


// Wind objects are instruments
// because they have the same interface:
//Wind管乐器;
public class Wind extends Instrument {
    // Redefine interface method:
    public void play(Note n) {
        System.out.println("Wind.play() " + n);
    }
    public void clearSpitValve(){
        System.out.println("Wind.clearSpitValve() ");
    }
} ///:~
