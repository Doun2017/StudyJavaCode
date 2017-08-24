//: enumerated/cartoons/EnumImplementation.java
// An enum can implement an interface
package com.wsn.chapter19enum;
//package enumerated.cartoons;

import java.util.*;
//import net.mindview.util.*;

enum CartoonCharacter {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    static private Random rand = new Random(47);

    static public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}

public class EnumImplementation {
    public static <T> void printNext() {
        System.out.print(CartoonCharacter.next() + ", ");
    }

    public static void main(String[] args) {
        // Choose any instance:
        for (int i = 0; i < 10; i++)
            printNext();
    }
} /* Output:
BOB, PUNCHY, BOB, SPANKY, NUTTY, PUNCHY, SLAPPY, NUTTY, NUTTY, SLAPPY,
*///:~
