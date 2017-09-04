//: concurrency/SerialNumberGenerator.java
package com.example.doun.chapter21concurrency;

public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public synchronized static int nextSerialNumber() {
        return serialNumber++; // Not thread-safe
    }
} ///:~
