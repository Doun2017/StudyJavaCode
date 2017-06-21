//: exceptions/Switch.java
package com.wsn.chapter12exception;

import android.util.Log;


public class Switch {
    private boolean state = false;

    public boolean read() {
        return state;
    }

    public void on() {
        state = true;
        Log.d("Switch", this.toString());
    }

    public void off() {
        state = false;
        Log.d("Switch", this.toString());
    }

    public String toString() {
        return state ? "on" : "off";
    }
} ///:~
