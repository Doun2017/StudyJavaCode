//: exceptions/StormyInning29.java
// Overridden methods may throw only the exceptions
// specified in their base-class versions, or exceptions
// derived from the base-class exceptions.
package com.wsn.chapter12exception;

class UmpireArgument29 extends RuntimeException {
}

class BaseballException29 extends RuntimeException {
}

class Foul29 extends BaseballException29 {
}

class Strike29 extends BaseballException29 {
}

abstract class Inning29 {
    public Inning29() {
    }

    public void event() {
        // Doesn't actually have to throw anything
    }

    //多态效果（根据对象类型调用）
    //static 使此函数不具有多态效果(根据引用类型进行调用)；如果加上final关键字，子类就不能重写这个函数了。如果改为private 则子类会覆盖掉这个函数。
    static public void addFunc() {
        // Doesn't actually have to throw anything
        throw new UmpireArgument29();
    }

    private void p(){};

    public abstract void atBat() ;

    public void walk() {
    } // Throws no checked exceptions
}

class StormException29 extends RuntimeException {
}

class RainedOut29 extends StormException29 {
}

class PopFoul29 extends Foul29 {
}

interface Storm29 {
    public void event() ;

    public void rainHard() ;
}

public class StormyInning29 extends Inning29 implements Storm29 {
    // OK to add new exceptions for constructors, but you
    // must deal with the base constructor exceptions:
    public StormyInning29()
            {
//        try{
//        }catch (BaseballException29 e){
//        }
    }

    public StormyInning29(String s)
             {
    }

    // Regular methods must conform to base class:
    public void walk()  {} //Compile error
    // Interface CANNOT add exceptions to existing
    // methods from the base class:
//    public void event() {}
    // If the method doesn't already exist in the
    // base class, the RuntimeException is OK:
    public void rainHard() {
    }

    // You can choose to not throw any exceptions,
    // even if the base version does:
    public void event() {
    }

    static public void addFunc() {
        // Doesn't actually have to throw anything
//        super.p();
        return;
    }

    // Overridden methods can throw inherited exceptions:
    public void atBat() {
        throw new PopFoul29();
    }

    public static void main(String[] args) {
        try {
            StormyInning29 si = new StormyInning29();
            si.atBat();
        } catch (PopFoul29 e) {
            System.out.println("Pop Foul29");
        } catch (RainedOut29 e) {
            System.out.println("Rained out");
        } catch (BaseballException29 e) {
            System.out.println("Generic baseball RuntimeException");
        }
        // Strike29 not thrown in derived version.
        try {
            // What happens if you upcast?
            Inning29 i = new StormyInning29();
            i.atBat();
            // You must catch the exceptions from the
            // base-class version of the method:
        } catch (Strike29 e) {
            System.out.println("Strike29");
        } catch (Foul29 e) {
            System.out.println("Foul29");
        } catch (RainedOut29 e) {
            System.out.println("Rained out");
        } catch (BaseballException29 e) {
            System.out.println("Generic baseball RuntimeException");
        }
    }
} ///:~
