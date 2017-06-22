package com.wsn.chapter12exception;

import android.util.Log;

/**
 * Created by power on 2017/6/22,022.
 */
class FirstException extends Exception{}
class SecondException extends Exception{}

public class FailingConstructor1 {
    private int firstMustBeZero = 0;
    private int secondMustBeZero = 0;

    public FailingConstructor1(int first, int second) throws FirstException, SecondException{
        if (first==0){
            Log.e("FailingConstructor1", " Constructor " + "first==0");
            throw new FirstException();
        }
        if (second==0){
            Log.e("FailingConstructor1", " Constructor " + "second==0");
            throw new SecondException();
        }
        firstMustBeZero = first;
        secondMustBeZero = second;
    }

    public void doSomething(){
        Log.e("FailingConstructor1", "soSomething  "+firstMustBeZero+"  "+secondMustBeZero);
    }

    public void dispose(){
        firstMustBeZero = 0;
        secondMustBeZero = 0;
        Log.e("FailingConstructor1", "dispose");
    }
}
