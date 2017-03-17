package com.doun.chapter9interface;

import android.util.Log;

/**
 * Created by power on 2017/3/17,017.
 */

public abstract class BasePrint {
    public int baseIntToPrint = 1;
    public BasePrint() {
        print();
    }

    abstract public void print();
}

class ExtentPrint extends BasePrint{
    public int intToPrint = 4;

    @Override
    public void print() {
        Log.i("baseIntToPrint", ""+baseIntToPrint);
        Log.i("intToPrint", ""+intToPrint);
    }
}