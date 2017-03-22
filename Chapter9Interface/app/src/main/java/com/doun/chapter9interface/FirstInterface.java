package com.doun.chapter9interface;

import android.util.Log;

/**
 * Created by power on 2017/3/22,022.
 */

public interface FirstInterface {
    void first();
}

interface SecondInterface extends FirstInterface{
    void second();

}

interface ThirdInterface extends FirstInterface{
    void third();

}

interface FourthInterface extends SecondInterface, ThirdInterface{

}

class ImplementClass implements FourthInterface{
    @Override
    public void first() {
        Log.i("ImplementClass", "first run");
    }

    @Override
    public void second() {
        Log.i("ImplementClass", "second run");

    }

    @Override
    public void third() {
        Log.i("ImplementClass", "third run");

    }
}