package com.example.doun.chapter15generics;

import android.util.Log;

/**
 * Created by Doun on 2017/7/15.
 */

public interface HasTwoFunc {
    void func1();
    void func2();
}

class ClassHasThreeFunc implements HasTwoFunc{
    @Override
    public void func1() {
        Log.d("ClassHasThreeFunc", "func1 in ClassHasThreeFunc");
    }

    @Override
    public void func2() {
        Log.d("ClassHasThreeFunc", "func2 in ClassHasThreeFunc");
    }

    public void func3() {
        Log.d("ClassHasThreeFunc", "func3 in ClassHasThreeFunc");

    }
}