package com.doun.chapter8polymorphic;

import android.util.Log;

import java.util.Random;

/**
 * Created by power on 2017/3/14,014.
 */
class AlertStatus{
    public void alert(){
    }
}

class RedAlertStatus extends AlertStatus{
    @Override
    public void alert() {
        Log.i("AlertStatus", "RedAlertStatus");
    }
}


class YellowAlertStatus extends AlertStatus{
    @Override
    public void alert() {
        Log.i("AlertStatus", "YellowAlertStatus");
    }
}

class GreenAlertStatus extends AlertStatus{
    @Override
    public void alert() {
        Log.i("AlertStatus", "GreenAlertStatus");
    }
}


public class StarShip {
    AlertStatus alertStatus = new GreenAlertStatus();

    public void change() {
        Random random = new Random();
        int i = random.nextInt(3);
        switch (i){
            case 0:
                alertStatus = new GreenAlertStatus();
                break;
            case 1:
                alertStatus = new YellowAlertStatus();
                break;
            case 2:
                alertStatus = new RedAlertStatus();
                break;
            default:
                break;
        }

    }
    public void performPlay() { alertStatus.alert(); }
}
