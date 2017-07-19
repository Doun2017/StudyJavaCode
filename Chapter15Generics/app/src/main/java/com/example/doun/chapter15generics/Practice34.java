package com.example.doun.chapter15generics;

import android.util.Log;

/**
 * Created by power on 2017/7/19,019.
 */

abstract class SelfBoundSetterAndGetter<T extends SelfBoundSetterAndGetter<T>> {
    abstract T func(T t);
    T callFunc(T t){
        return func(t);
    }
}

public class Practice34 extends SelfBoundSetterAndGetter<Practice34>{
    static int count=0;
    int mynum=0;
    Practice34(){
        count++;
        mynum = count;
    }

    @Override
    public String toString() {
        return "Practice34:"+mynum;
    }

    @Override
    Practice34 func(Practice34 practice34) {
        Log.d("Practice34", "func-" + practice34.toString());
        return new Practice34();
    }
}
