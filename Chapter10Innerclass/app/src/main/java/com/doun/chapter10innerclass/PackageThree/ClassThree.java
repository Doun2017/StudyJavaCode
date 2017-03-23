package com.doun.chapter10innerclass.PackageThree;

import com.doun.chapter10innerclass.PackageOne.InterfaceOne;
import com.doun.chapter10innerclass.PackageTwo.OutClassTwo;

/**
 * Created by power on 2017/3/23,023.
 */

public class ClassThree extends OutClassTwo {
    public InterfaceOne getInnerClass(){
        return new InnerClassTwo();
    }
}
