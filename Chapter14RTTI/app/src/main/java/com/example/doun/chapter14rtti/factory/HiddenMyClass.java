package com.example.doun.chapter14rtti.factory;


/**
 * Created by Doun on 2017/7/8.
 */

public class HiddenMyClass {
    public static Factory<HiddenMyClass> makeFactory() { return new MyClass(); }
}


class MyClass extends HiddenMyClass implements Factory<HiddenMyClass>  {
    private void myPrivateFun(){
        System.out.println("myPrivateFun");
    }

    protected void myProtectedFun(){
        System.out.println("myProtectedFun");
    }

    public void myPublicFun(){
        System.out.println("myPublicFun");
    }


    @Override
    public HiddenMyClass create() {
        return new MyClass();
    }
}