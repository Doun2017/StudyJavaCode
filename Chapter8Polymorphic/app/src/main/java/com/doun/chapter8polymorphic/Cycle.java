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

    public String balance(){
        return "Cycle Balance";
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
    public String balance(){
        return "Unicycle Balance";
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

    public String balance(){
        return "Bicycle Balance";
    }
}

class Tricycle extends Cycle{
    public static String TAG = "Tricycle   ";
    @Override
    public String beRide() {
        return TAG + "Tricycle ride.";
    }

}
