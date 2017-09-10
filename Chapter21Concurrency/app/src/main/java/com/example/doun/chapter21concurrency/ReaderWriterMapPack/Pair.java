//: net/mindview/util/Pair.java
//1package net.mindview.util;
package com.example.doun.chapter21concurrency.ReaderWriterMapPack;

public class Pair<K, V> {
    public final K key;
    public final V value;

    public Pair(K k, V v) {
        key = k;
        value = v;
    }
} ///:~
