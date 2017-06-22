package com.wsn.chapter12exception;

import android.util.Log;

/**
 * Created by power on 2017/6/22,022.
 */

public class FailingConstructor {

    public FailingConstructor(int in) throws Exception{
        if (in%2==1){
            Log.e("FailingConstructor", " Constructor "+in);
            throw new Exception();
        }
    }

    public void doSomething(){
        Log.e("FailingConstructor", "soSomething");
    }

    public void dispose(){
        Log.e("FailingConstructor", "dispose");
    }
}
