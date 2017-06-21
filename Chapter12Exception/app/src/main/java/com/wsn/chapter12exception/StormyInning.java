//: exceptions/StormyInning.java
// Overridden methods may throw only the exceptions
// specified in their base-class versions, or exceptions
// derived from the base-class exceptions.
package com.wsn.chapter12exception;

class UmpireArgument extends Exception {
}

class BaseballException extends Exception {
}

class Foul extends BaseballException {
}

class Strike extends BaseballException {
}

abstract class Inning {
    public Inning() throws BaseballException {
    }

    public void event() throws BaseballException {
        // Doesn't actually have to throw anything
    }

    //多态效果（根据对象类型调用）
    //static 使此函数不具有多态效果(根据引用类型进行调用)；如果加上final关键字，子类就不能重写这个函数了。如果改为private 则子类会覆盖掉这个函数。
    static public void addFunc() throws UmpireArgument {
        // Doesn't actually have to throw anything
        throw new UmpireArgument();
    }

    private void p(){};

    public abstract void atBat() throws Strike, Foul;

    public void walk() {
    } // Throws no checked exceptions
}

class StormException extends Exception {
}

class RainedOut extends StormException {
}

class PopFoul extends Foul {
}

interface Storm {
    public void event() throws RainedOut;

    public void rainHard() throws RainedOut;
}

public class StormyInning extends Inning implements Storm {
    // OK to add new exceptions for constructors, but you
    // must deal with the base constructor exceptions:
    public StormyInning()
            throws RainedOut, BaseballException {
//        try{
//        }catch (BaseballException e){
//        }
    }

    public StormyInning(String s)
            throws Foul, BaseballException {
    }

    // Regular methods must conform to base class:
//! void walk() throws PopFoul {} //Compile error
    // Interface CANNOT add exceptions to existing
    // methods from the base class:
//! public void event() throws RainedOut {}
    // If the method doesn't already exist in the
    // base class, the exception is OK:
    public void rainHard() throws RainedOut {
    }

    // You can choose to not throw any exceptions,
    // even if the base version does:
    public void event() {
    }

    static public void addFunc() throws UmpireArgument {
        // Doesn't actually have to throw anything
//        super.p();
        return;
    }

    // Overridden methods can throw inherited exceptions:
    public void atBat() throws PopFoul {
        throw new PopFoul();
    }

    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        } catch (PopFoul e) {
            System.out.println("Pop foul");
        } catch (RainedOut e) {
            System.out.println("Rained out");
        } catch (BaseballException e) {
            System.out.println("Generic baseball exception");
        }
        // Strike not thrown in derived version.
        try {
            // What happens if you upcast?
            Inning i = new StormyInning();
            i.atBat();
            // You must catch the exceptions from the
            // base-class version of the method:
        } catch (Strike e) {
            System.out.println("Strike");
        } catch (Foul e) {
            System.out.println("Foul");
        } catch (RainedOut e) {
            System.out.println("Rained out");
        } catch (BaseballException e) {
            System.out.println("Generic baseball exception");
        }
    }
} ///:~
