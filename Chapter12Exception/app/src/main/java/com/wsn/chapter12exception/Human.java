//: exceptions/Human.java
// Catching exception hierarchies.
package com.wsn.chapter12exception;

import java.io.FileNotFoundException;
import java.io.IOException;

class Annoyance extends RuntimeException {}

class Sneeze extends Annoyance {}

class WrapAnnoyance {
    static void throwRuntimeException() {
        try{
            throw new Annoyance();
        }
        catch(Exception e) { // Adapt to unchecked:
            throw new RuntimeException(e);
        }
    }
}

class WrapSneeze {
    static void throwRuntimeException() {
        try{
            throw new Sneeze();
        }
        catch(Exception e) { // Adapt to unchecked:
            throw new RuntimeException(e);
        }
    }
}


public class Human {
    public static void main(String[] args) {
        // Catch the exact type:

        try {
            WrapAnnoyance.throwRuntimeException();
//            throw new Sneeze();
        } catch (RuntimeException re) {
            try {
                throw re.getCause();
            } catch (Sneeze s) {
                System.out.println("Caught Sneeze");
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
            }catch (Throwable e) {
                System.out.println("Throwable: " + e);
            }
        }

        // Catch the base type:
        try {
            WrapSneeze.throwRuntimeException();
//            throw new Sneeze();
        } catch (RuntimeException re) {
            try {
                throw re.getCause();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
            } catch (Throwable e) {
                System.out.println("Throwable: " + e);
            }
        }



    }
} /* Output:
Caught Sneeze
Caught Annoyance
*///:~
