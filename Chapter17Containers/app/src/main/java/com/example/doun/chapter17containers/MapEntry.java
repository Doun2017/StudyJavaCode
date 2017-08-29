//: containers/MapEntry.java
// A simple Map.Entry for sample Map implementations.
package com.example.doun.chapter17containers;

import java.util.*;

public class MapEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private MapEntry<K, V> lastOne = null;
    private V value;

    public MapEntry(MapEntry<K, V> lastOne, K key, V value) {
        this.lastOne = lastOne;
        this.key = key;
        this.value = value;
    }

    public MapEntry(K key, V value) {
        this.lastOne = null;
        this.key = key;
        this.value = value;
    }
    public boolean haslast() {
        return lastOne!=null;
    }
    public MapEntry<K, V> getlast() {
        return lastOne;
    }
    public void setlast(MapEntry<K, V> mapEntry) {
        this.lastOne = mapEntry;
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V v) {
        V result = value;
        value = v;
        return result;
    }

    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^
                (value == null ? 0 : value.hashCode());
    }

    public boolean equals(Object o) {
        if (!(o instanceof MapEntry)) return false;
        MapEntry me = (MapEntry) o;
        return
                (key == null ?
                        me.getKey() == null : key.equals(me.getKey())) &&
                        (value == null ?
                                me.getValue() == null : value.equals(me.getValue()));
    }

    public String toString() {
        return key + "=" + value;
    }
} ///:~
