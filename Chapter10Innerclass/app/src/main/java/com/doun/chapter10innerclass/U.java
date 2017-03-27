package com.doun.chapter10innerclass;

import android.util.Log;

/**
 * Created by power on 2017/3/27,027.
 */

public interface U {
    void f1();
    void f2();
    void f3();
}

class A{
    String mark;

    public A(String mark) {
        this.mark = mark;
    }

    public U getU(){
        return new U() {
            @Override
            public void f1() {
                Log.i("A"+mark, "f1");
            }

            @Override
            public void f2() {
                Log.i("A"+mark, "f2");
            }

            @Override
            public void f3() {
                Log.i("A"+mark, "f3");
            }
        };
    }
}

class B{
    private U uInB[] = new U[3];
    private int mIndex =0;

    public void setUInB(U u){
        if (mIndex <3){
            uInB[mIndex] = u;
            mIndex++;
        }

        return;
    }
    public void setNull(int index){
        if (0<=index && index<3)
            uInB[index] = null;
    }
    public void eachUInB(){
        for (int i=0;i<3;i++){
            U u = uInB[i];
            if (u!=null){
                u.f1();
                u.f2();
                u.f3();
            }
        }
    }


}
