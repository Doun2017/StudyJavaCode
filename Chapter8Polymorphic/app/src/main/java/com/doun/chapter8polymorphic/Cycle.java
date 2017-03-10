package com.doun.chapter8polymorphic;



/**
 * Created by power on 2017/3/10,010.
 */

public class Cycle {
    public static String TAG = "Cycle   ";
    public String beRide(){
        return TAG + "Cycle ride.";
    }
    public String wheels(){
        return TAG + "0";
    }
}

class Unicycle extends Cycle{
    public static String TAG = "Unicycle   ";

    @Override
    public String beRide() {
        return TAG + "Unicycle ride.";
    }

    public String wheels(){
        return TAG + "1";
    }
}

class Bicycle extends Cycle{
    public static String TAG = "Bicycle   ";
    @Override
    public String beRide() {
        return TAG + "Bicycle ride.";
    }

    public String wheels(){
        return TAG + "2";
    }
}

class Tricycle extends Cycle{
    public static String TAG = "Tricycle   ";
    @Override
    public String beRide() {
        return TAG + "Tricycle ride.";
    }

}
