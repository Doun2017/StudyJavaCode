package com.doun.chapter9interface;

/**
 * Created by power on 2017/3/17,017.
 */

public abstract class TestAbstractClass {

}

abstract class NoMethods {
}

class Extended1 extends NoMethods {
    public void f() {
        System.out.println("Extended1.f");
    }
}

abstract class WithMethods {
    abstract public void f();
}

class Extended2 extends WithMethods {
    public void f() {
        System.out.println("Extended2.f");
    }
}

class E04_AbstractBase {
    public static void test1(NoMethods nm) {
        // Must downcast to access f():必须向下转型
        ((Extended1)nm).f();
    }

    public static void test2(WithMethods wm) {
        // No downcast necessary:不必向下转型，因为f在基类中有定义
        wm.f();
    }

    public static void main(String args[]) {
        NoMethods nm = new Extended1();
        test1(nm);
        WithMethods wm = new Extended2();
        test2(wm);
    }
}
