//: interfaces/interfaceprocessor/Apply.java
package com.doun.chapter9interface;


import android.util.Log;

public class Apply {
  public static void process(Processor p, Object s) {
    Log.i("Apply", "Using Processor " + p.name());
    Log.i("Apply", "Process :"+p.process(s));
  }
} ///:~
