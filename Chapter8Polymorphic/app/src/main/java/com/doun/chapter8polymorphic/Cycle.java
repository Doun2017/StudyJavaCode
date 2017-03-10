package com.doun.chapter8polymorphic;



/**
 * Created by power on 2017/3/10,010.
 */

public class Cycle {
    public static String TAG = "Cycle   ";
    public String ride(){
        return TAG + "Cycle ride.";
    }
}

class Unicycle extends Cycle{
    @Override
    public String ride() {
        return TAG + "Unicycle ride.";
    }
}

class Bicycle extends Cycle{
    @Override
    public String ride() {
        return TAG + "Bicycle ride.";
    }
}

class Tricycle extends Cycle{
    @Override
    public String ride() {
        return TAG + "Tricycle ride.";
    }
}
