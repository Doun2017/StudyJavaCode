package com.doun.chapter10innerclass;

/**
 * Created by power on 2017/3/23,023.
 */

public class E07practiceClass {
    private String str;
    private void f(){}

    public String useInnerClass(){
        InnerClass innerClass = new InnerClass();
        innerClass.changeStr();
//        String temStr = strPrivate;
        String temStr = innerClass.strPrivate;

        return temStr;
    }

    class InnerClass{
        private String strPrivate="InnerClass:strPrivate";
        void changeStr(){
            str = "changed in InnerClass";
            f();
        }
    }
}
