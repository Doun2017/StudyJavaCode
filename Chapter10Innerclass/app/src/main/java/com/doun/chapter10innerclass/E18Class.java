package com.doun.chapter10innerclass;

/**
 * Created by power on 2017/3/27,027.
 */

public class E18Class {
    private static int fa=2;

    static class E18InnerClass{
        private int tt=0;
        public int getfa(){
            return fa;
        }
        static class E18InnerInnerClass {

        }
    }
}

class E18Class1 {
    private static int fa=2;

    public class E18InnerClass1{
        private int tt=0;
        public int getfa(){
            return fa;
        }
        class E18InnerInnerClass1 {

        }
    }
}
