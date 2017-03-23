package com.doun.chapter10innerclass;

/**
 * Created by power on 2017/3/23,023.
 */

public class E10practiceInterface {
    E10practiceInterface f(){
        return fun();
    }

    E10practiceInterface fun(){
        class E10practiceClass extends E10practiceInterface{
            @Override
            public E10practiceInterface f() {
                return new E10practiceClass();
            }
        }
        E10practiceClass e10 = new E10practiceClass();
        return e10;
    }
}
