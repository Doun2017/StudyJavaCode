//: io/Blips.java
// Simple use of Externalizable & a pitfall.
package com.wsn.chapter18javaio;

import java.io.*;

//import static net.mindview.util.System.out.println.*;

class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    public void writeExternal(ObjectOutput out)
            throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}

public class BlipCheck implements Externalizable {
//    BlipCheck() {
//        System.out.println("BlipCheck Constructor");
//    }

    public void writeExternal(ObjectOutput out)
            throws IOException {
        System.out.println("BlipCheck.writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("BlipCheck.readExternal");
    }
}

class Blips {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip1 b1 = new Blip1();
        BlipCheck b2 = new BlipCheck();
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Blips.out"));
        System.out.println("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blips.out"));
        System.out.println("Recovering b1:");
        b1 = (Blip1) in.readObject();
        // OOPS! Throws an exception:
        System.out.println("Recovering b2:");
        b2 = (BlipCheck)in.readObject();
    }
} /* Output:
Constructing objects:
Blip1 Constructor
BlipCheck Constructor
Saving objects:
Blip1.writeExternal
BlipCheck.writeExternal
Recovering b1:
Blip1 Constructor
Blip1.readExternal
*///:~
