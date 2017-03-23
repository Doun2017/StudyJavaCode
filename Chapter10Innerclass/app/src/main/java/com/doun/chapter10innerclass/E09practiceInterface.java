package com.doun.chapter10innerclass;

/**
 * Created by power on 2017/3/23,023.
 */

public interface E09practiceInterface {
    E09practiceInterface f();

    class E09practiceClass implements E09practiceInterface{
        @Override
        public E09practiceInterface f() {
            return new E09practiceClass();
        }
    }
}
