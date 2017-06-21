//: reusing/CADSystem.java
// Ensuring proper cleanup.
package com.wsn.chapter12exception;


import android.util.Log;

class Shape {
    Shape(int i) {
        Log.e("Shap", "Shape constructor");
    }

    void dispose() {
        Log.e("Shap", "Shape dispose");
    }
}

class Circle extends Shape {
    Circle(int i) {
        super(i);
        Log.e("Shap", "Drawing Circle");
    }

    void dispose() {
        Log.e("Shap", "Erasing Circle");
        super.dispose();
    }
}

class Triangle extends Shape {
    Triangle(int i) {
        super(i);
        Log.e("Shap", "Drawing Triangle");
    }

    void dispose() {
        Log.e("Shap", "Erasing Triangle");
        super.dispose();
    }
}

class Line extends Shape {
    private int start, end;

    Line(int start, int end) {
        super(start);
        this.start = start;
        this.end = end;
        Log.e("Shap", "Drawing Line: " + start + ", " + end);
    }

    void dispose() {
        Log.e("Shap", "Erasing Line: " + start + ", " + end);
        super.dispose();
    }
}

public class CADSystem extends Shape {
    private Circle c;
    private Triangle t;
    private Line[] lines = new Line[3];

    public CADSystem(int i) {
        super(i + 1);
        for (int j = 0; j < lines.length; j++)
            lines[j] = new Line(j, j * j);
        c = new Circle(1);
        t = new Triangle(1);
        Log.e("Shap", "Combined constructor");
    }

    public void dispose() {
        Log.e("Shap", "CADSystem.dispose()");
        // The order of cleanup is the reverse
        // of the order of initialization:
        t.dispose();
        c.dispose();
        for (int i = lines.length - 1; i >= 0; i--)
            lines[i].dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        CADSystem x = new CADSystem(47);
        try {
            // Code and exception handling...
        } finally {
            x.dispose();
        }
    }
} /* Output:
Shape constructor
Shape constructor
Drawing Line: 0, 0
Shape constructor
Drawing Line: 1, 1
Shape constructor
Drawing Line: 2, 4
Shape constructor
Drawing Circle
Shape constructor
Drawing Triangle
Combined constructor
CADSystem.dispose()
Erasing Triangle
Shape dispose
Erasing Circle
Shape dispose
Erasing Line: 2, 4
Shape dispose
Erasing Line: 1, 1
Shape dispose
Erasing Line: 0, 0
Shape dispose
Shape dispose
*///:~
