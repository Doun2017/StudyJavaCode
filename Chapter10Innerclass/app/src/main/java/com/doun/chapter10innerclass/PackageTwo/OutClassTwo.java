package com.doun.chapter10innerclass.PackageTwo;

import com.doun.chapter10innerclass.PackageOne.InterfaceOne;

/**
 * Created by power on 2017/3/23,023.
 */

public class OutClassTwo {
    protected class InnerClassTwo implements InterfaceOne{
        //默认构造函数与内部类一样是有protected权限控制，
        //所以这里声明一个public的构造函数，以便在ClassThree中能创建内部类对象
        public InnerClassTwo() {
        }

        @Override
        public void f() {
            return;
        }
    }
}
