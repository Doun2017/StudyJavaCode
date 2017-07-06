//: typeinfo/Shapes.java
package com.example.doun.chapter14rtti;

import java.util.*;

abstract class Shape {
    boolean signal=false;
    void draw() {
        System.out.println(this + ".draw()");
    }
    void rotate() {
        System.out.println(this + ".rotate()");
    }

    abstract public String toString();
}

class Circle extends Shape {
    public Circle() {
        signal = true;
    }
    public String toString() {
        return "Circle signal = "+signal;
    }
}

class Square extends Shape {
    public String toString() {
        return "Square signal = "+signal;
    }
}

class Triangle extends Shape {
    public String toString() {
        return "Triangle signal = "+signal;
    }
}

class Rhomboid extends Shape {
    public String toString() {
        signal = true;
        return "Rhomboid signal = "+signal;
    }
}

public class Shapes {
    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(
                new Circle(), new Square(), new Triangle()
        );
        for (Shape shape : shapeList)
            shape.draw();
    }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
