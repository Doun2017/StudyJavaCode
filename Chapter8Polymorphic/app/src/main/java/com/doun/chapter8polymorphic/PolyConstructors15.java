//: polymorphism/PolyConstructors.java
// Constructors and polymorphism
// don't produce what you might expect.
package com.doun.chapter8polymorphic;

import android.util.Log;


class Glyph {
    void draw() {
        Log.i("Glyph", "Glyph.draw()");
    }

    Glyph() {
        Log.i("Glyph", "Glyph() before draw()");
        draw();
        Log.i("Glyph", "Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        Log.i("Glyph", "RoundGlyph.RoundGlyph(), radius = " + radius);
    }

    void draw() {
        Log.i("Glyph", "RoundGlyph.draw(), radius = " + radius);
    }
}

class RectangularGlyph extends Glyph {
    private int longSide = 5;
    private int shortSide = 2;

    RectangularGlyph(int longSide, int shortSide) {
        this.longSide = longSide;
        this.shortSide = shortSide;
        Log.i("Glyph", "RectangularGlyph.RectangularGlyph(), longSide = " + longSide);
    }

    void draw() {
        Log.i("Glyph", "RectangularGlyph.draw(), longSide = " + longSide);
    }
}

public class PolyConstructors15 {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
} /* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
*///:~
