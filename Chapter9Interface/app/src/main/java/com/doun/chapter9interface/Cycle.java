package com.doun.chapter9interface;

/**
 * Created by Doun on 2017/3/22.
 */

public interface Cycle {
    void f();
}

interface CycleFactory {

}

class Unicycle implements Cycle {
    @Override
    public void f() {

    }
}

class UnicycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Unicycle();
    }
}

class Bicycle implements Cycle {
    @Override
    public void f() {

    }
}

class BicycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Bicycle();
    }
}

class Tricycle implements Cycle {
    @Override
    public void f() {

    }
}

class TricycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Tricycle();
    }
}
