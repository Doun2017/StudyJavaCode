//: enumerated/TrafficLight.java
// Enums in switch statements.
package com.wsn.chapter19enum;

//import static net.mindview.util.Print.*;

import static com.wsn.chapter19enum.Signal.*;

// Define an enum type:
enum Signal {
    GREEN, YELLOW, RED,
}

public class TrafficLight {
    Signal color = RED;

    public void change() {
        switch (color) {
            // Note that you don't have to say Signal.RED
            // in the case statement:
            case RED:
                color = GREEN;
                break;
            case GREEN:
                color = YELLOW;
                break;
            case YELLOW:
                color = RED;
                break;
        }
    }

    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            System.out.println(t);
            t.change();
        }
    }
} /* Output:
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
*///:~
