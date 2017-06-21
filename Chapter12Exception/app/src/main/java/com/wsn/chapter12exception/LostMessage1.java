//: exceptions/LostMessage1.java
// How an exception can be lost.

//修复异常丢失
package com.wsn.chapter12exception;

class VeryImportantException1 extends Exception {
    public String toString() {
        return "A very important exception!";
    }
}

class HoHumException1 extends Exception {
    public String toString() {
        return "A trivial exception";
    }
}

public class LostMessage1 {
    void f() throws VeryImportantException1 {
        throw new VeryImportantException1();
    }

    void dispose() throws HoHumException1 {
        throw new HoHumException1();
    }

    public static void main(String[] args) {
        try {
            LostMessage1 lm = new LostMessage1();
            try {
                lm.f();
            } finally {
                lm.dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
} /* Output:
A trivial exception
*///:~
