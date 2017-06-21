//: exceptions/LostMessage.java
// How an exception can be lost.
package com.wsn.chapter12exception;

import android.util.Log;

class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception!";
    }
}

class HoHumException extends Exception {
    public String toString() {
        return "A trivial exception";
    }
}

class AnotherHoHumException extends Exception {
    public String toString() {
        return "A another trivial exception";
    }
}
public class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }
    void antherDispose() throws AnotherHoHumException {
        throw new AnotherHoHumException();
    }
    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally {
                lm.dispose();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
} /* Output:
A trivial exception
*///:~
