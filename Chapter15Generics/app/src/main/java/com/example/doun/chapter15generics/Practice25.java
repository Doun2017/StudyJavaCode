package com.example.doun.chapter15generics;

/**
 * Created by Doun on 2017/7/17.
 */
interface FirstInterface{
}
interface SecondInterface{

}
public class Practice25 implements FirstInterface, SecondInterface{
    static public <T extends FirstInterface> void func1(T t){

    }
    static public <T extends SecondInterface> void func2(T t) {

    }
}
