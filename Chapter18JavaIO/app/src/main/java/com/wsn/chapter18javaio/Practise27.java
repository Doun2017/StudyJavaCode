package com.wsn.chapter18javaio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by power on 2017/8/22,022.
 */
class Practise27Data implements Serializable {
    int anInt = 3;

    @Override
    public String toString() {
        return "Practise27Data{" +
                "anInt=" + anInt +
                '}';
    }
}
public class Practise27 implements Serializable {
    Practise27Data practise27Data;
    long aLong = 33342;
    Practise27(){
        practise27Data = new Practise27Data();
    }

    @Override
    public String toString() {
        return "Practise27{" +
                "practise27Data=" + practise27Data +
                ", aLong=" + aLong +
                '}';
    }

    public static void main(String[] args)
            throws ClassNotFoundException, IOException {
        Practise27 w = new Practise27();
        System.out.println(w);
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("Practise27.out"));
        out.writeObject(w);
        out.close(); // Also flushes output
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Practise27.out"));
        Practise27 w2 = (Practise27)in.readObject();
        System.out.println(w2);

//        ByteArrayOutputStream bout =
//                new ByteArrayOutputStream();
//        ObjectOutputStream out2 = new ObjectOutputStream(bout);
//        out2.writeObject("Worm storage\n");
//        out2.writeObject(w);
//        out2.flush();
//        ObjectInputStream in2 = new ObjectInputStream(
//                new ByteArrayInputStream(bout.toByteArray()));
//        s = (String)in2.readObject();
//        Worm w3 = (Worm)in2.readObject();
//        print(s + "w3 = " + w3);
    }
}
