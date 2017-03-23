package com.doun.chapter10innerclass;

/**
 * Created by power on 2017/3/23,023.
 */
interface Forcall{
    void f();
}
public class E11practiceClass {
    public Forcall getForcall(){
        E11InnerClass innerClass = new E11InnerClass();
        return innerClass;
    }
    private class E11InnerClass implements Forcall{
        @Override
        public void f() {

        }
    }
}
