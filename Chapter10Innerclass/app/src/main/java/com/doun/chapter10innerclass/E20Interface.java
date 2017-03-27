package com.doun.chapter10innerclass;

/**
 * Created by power on 2017/3/27,027.
 */

public interface E20Interface {

    int getInt();
    class InnerClassE20{
        int getInnerInt(){
            return 1;
        }

        static int getInterfaceInt(E20Interface e20Interface){
            return e20Interface.getInt();
        }
    }
}

class E20class implements E20Interface{
    public int getInt(){
        return 4;
    }
}