//: polymorphism/ReferenceCounting.java
// Cleaning up shared member objects.
package com.doun.chapter8polymorphic;

import android.util.Log;
class Shared {
    private int refCount = 0;
    private static long counter = 0;
    private final long id = counter++;

    public Shared() {
        Log.i("Shared", "Creating " + this);
    }

    @Override
    protected void finalize() throws Throwable {
        Log.i("Shared", "finalizing 此时refCount=" + refCount);

        super.finalize();
    }

    public void addRef() {
        refCount++;
    }

    protected void dispose() {
        if (--refCount == 0)
            Log.i("Shared", "Disposing " + this);
    }

    public String toString() {
        return "Shared " + id;
    }
}

class Composing {
    private Shared shared;
    private static long counter = 0;
    private final long id = counter++;

    public Composing(Shared shared) {
        Log.i("Composing", "Creating " + this);
        this.shared = shared;
        this.shared.addRef();
    }

    protected void dispose() {
        Log.i("Composing", "disposing " + this);
        shared.dispose();
    }

    public String toString() {
        return "Composing " + id;
    }
}

public class ReferenceCounting13 {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] composing = {new Composing(shared),
                new Composing(shared), new Composing(shared),
                new Composing(shared), new Composing(shared)};
        for (Composing c : composing)
            c.dispose();
    }
} /* Output:
Creating Shared 0
Creating Composing 0
Creating Composing 1
Creating Composing 2
Creating Composing 3
Creating Composing 4
disposing Composing 0
disposing Composing 1
disposing Composing 2
disposing Composing 3
disposing Composing 4
Disposing Shared 0
*///:~
