package com.doun.chapter10innerclass;

/**
 * Created by Doun on 2017/3/22.
 */

public interface Cycle {
    String f();
}

interface CycleFactory {
    Cycle getCycle();
}

class Unicycle implements Cycle {
    @Override
    public String f() {
        return "Unicycle";
    }
    static CycleFactory cycleFactory = new CycleFactory() {
        @Override
        public Cycle getCycle() {
            return new Unicycle();
        }
    };
}

class Bicycle implements Cycle {
    @Override
    public String f() {
        return "Bicycle";

    }
    static CycleFactory cycleFactory = new CycleFactory() {
        @Override
        public Cycle getCycle() {
            return new Bicycle();
        }
    };
}

class Tricycle implements Cycle {
    @Override
    public String f() {
        return "Tricycle";

    }
    static CycleFactory cycleFactory = new CycleFactory() {
        @Override
        public Cycle getCycle() {
            return new Tricycle();
        }
    };
}
