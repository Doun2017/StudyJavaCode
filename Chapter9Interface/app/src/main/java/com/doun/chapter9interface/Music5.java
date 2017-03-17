
package com.doun.chapter9interface;


import android.util.Log;

enum Note {
  MIDDLE_C, C_SHARP, B_FLAT; // Etc.
} ///:~

interface Playable{
  void play(Note note);
}

abstract class Instrument {
  // Compile-time constant:
  int VALUE = 5; // static & final
  // Cannot have method definitions:
  abstract void adjust();
}

class Wind extends Instrument implements Playable{
  public void play(Note n) {
    Log.i(this.getClass().getName(), this + ".play() " + n);
  }
  public String toString() { return "Wind"; }
  public void adjust() { Log.i(this.getClass().getName(), this + ".adjust()"); }
}

class Percussion extends Instrument implements Playable{
  public void play(Note n) {
    Log.i(this.getClass().getName(), this + ".play() " + n);
  }
  public String toString() { return "Percussion"; }
  public void adjust() { Log.i(this.getClass().getName(), this + ".adjust()"); }
}

class Stringed extends Instrument implements Playable{
  public void play(Note n) {
    Log.i(this.getClass().getName(), this + ".play() " + n);
  }
  public String toString() { return "Stringed"; }
  public void adjust() { Log.i(this.getClass().getName(), this + ".adjust()"); }
}

class Brass extends Wind {
  public String toString() { return "Brass"; }
}	

class Woodwind extends Wind {
  public String toString() { return "Woodwind"; }
}

public class Music5 {
  // Doesn't care about type, so new types
  // added to the system still work right:
  static void tune(Playable i) {
    // ...
    i.play(Note.MIDDLE_C);
  }
  static void tuneAll(Playable[] e) {
    for(Playable i : e)
      tune(i);
  }	
  public static void main(String[] args) {
    // Upcasting during addition to the array:
    Playable[] orchestra = {
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
