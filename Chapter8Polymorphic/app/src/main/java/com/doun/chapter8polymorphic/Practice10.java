package com.doun.chapter8polymorphic;

/**
 * Created by power on 2017/3/10,010.
 */

public class Practice10 {
    public String funOne(){
        return funTwo();
    }
    public String funTwo(){
        return "funTwo";
    }
}

class Practice10ImportClass extends Practice10{
    @Override
    public String funTwo() {
        return "ImportClass funTwo";
    }
}
