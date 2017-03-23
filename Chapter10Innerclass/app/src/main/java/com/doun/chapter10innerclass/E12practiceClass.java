package com.doun.chapter10innerclass;

/**
 * Created by Doun on 2017/3/23.
 */

public class E12practiceClass {
    private String str;
    private void f(){}

    public String useInnerClass(){
       String temStr = new E12practiceClass (){
            private String strPrivate="InnerClass:strPrivate";
            void changeStr(){
                str = "changed in InnerClass";
                f();
            }
        }.strPrivate;
        return temStr;
    }
}
