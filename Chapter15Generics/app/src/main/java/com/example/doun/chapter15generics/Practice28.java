package com.example.doun.chapter15generics;

import android.util.Log;

import com.example.doun.chapter15generics.pets.Cat;

/**
 * Created by power on 2017/7/18,018.
 */
class Generic1<T>{
    public void func(T t){
        System.out.println("Generic1:func called.");
    }
}

class Generic2<T>{
    T t;
    public T func(){
        System.out.println("Generic2:func called.");
        return t;
    }
}

public class Practice28 {
//    public static <? super Generic1> func1()
    static public <T> void func1(Generic1<? super T> generic1){
        T t = null;
        generic1.func(t);
    }
    static public <T> void func2(Generic2<? extends T> generic2){
        T t = null;
        t = generic2.func();
    }

    public static void main(String[] args) {

        Generic1<Cat> catGeneric1 = new Generic1<>();
        Practice28.func1(catGeneric1);

        Generic2<Cat> catGeneric2 = new Generic2<>();
        Practice28.func2(catGeneric2);

    }
}
