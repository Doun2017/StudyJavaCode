//: generics/ClassTypeCapture2.java
package com.example.doun.chapter15generics;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

interface FactoryII<T> {
    T create();
}

class Building2 {
    public static class Factory implements FactoryII<Building2> {
        public Building2 create() {
            return new Building2();
        }
    }
}

class House2 extends Building2 {
    public static class Factory implements FactoryII<House2> {
        public House2 create() {
            return new House2();
        }
    }
}

public class ClassTypeCapture2<T> {
    Class<T> kind;

    Map<String, FactoryII> kindsMap = new HashMap<>();

    public ClassTypeCapture2(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }

    public void addType(String typename, FactoryII f){
        kindsMap.put(typename, f);
    }

    public Object createNew(String typename){
        FactoryII f = kindsMap.get(typename);
        if (f == null){
            Log.e("ClassTypeCapture2", "没有"+typename+"类型");
            return null;
        }

        try {
            return f.create();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        ClassTypeCapture2<Building2> ctt1 = new ClassTypeCapture2<Building2>(Building2.class);
//        System.out.println(ctt1.f(new Building2()));
//        System.out.println(ctt1.f(new House2()));
//        ClassTypeCapture2<House2> ctt2 = new ClassTypeCapture2<House2>(House2.class);
//        System.out.println(ctt2.f(new Building2()));
//        System.out.println(ctt2.f(new House2()));
    }
} /* Output:
true
true
false
true
*///:~
