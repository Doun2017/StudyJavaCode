package com.doun.chapter10innerclass;

/**
 * Created by Doun on 2017/3/24.
 */

public class E15Class {
    private int b;
    public E15Class(int a) {
        b = a;
    }
    int getB(){return b;}
}

class AnotherE15Class{
    static public E15Class createE15(){
        return new E15Class(3){
        };
    }
}