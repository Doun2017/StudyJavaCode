package com.doun.chapter10innerclass;

/**
 * Created by power on 2017/3/23,023.
 */

public class Outer {
    private String outerString;
    @Override
    public String toString() {
        return outerString;
    }
    public Outer(String outerString) {
        this.outerString = outerString;
    }

    public Inner newInner() {
        return new Inner();
    }

    class Inner{
        @Override
        public String toString() {
            return outerString;
        }
    }
}
