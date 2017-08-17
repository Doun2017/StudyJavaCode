//: io/UsingRandomAccessFile.java
package com.wsn.chapter18javaio;

import java.io.*;
import java.util.Arrays;

public class UsingRandomAccessFile {
    static String file = "rtest.dat";

    static void display() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        for (int i = 0; i < 7; i++)
            System.out.println("Value " + i + ": " + rf.readDouble());
        System.out.println(rf.readUTF());
        rf.close();
    }
    static void display1() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");

        byte b1[] = new byte[10];
        byte b2[] = new byte[10];
        char c3[] = new char[10];

        System.out.println(rf.read(b2, 0, 4));
        System.out.println(Arrays.toString(b2));
        System.out.println(rf.readByte());
        System.out.println(rf.readBoolean());
        System.out.println(rf.readByte());
        rf.readFully(b1);
        System.out.println(Arrays.toString(b1));
        System.out.println(rf.readChar());
        for(int i = 0;i<10;i++){
            c3[i] = rf.readChar();
        }
        System.out.println(Arrays.toString(c3));

//        while(rf.available()>0)
//        {
//            // read character
//            char c = rf.readChar();
//            System.out.print(c);
//        }
        System.out.println(rf.readDouble());
        System.out.println(rf.readFloat());
        System.out.println(rf.readInt());
        System.out.println(rf.readLong());
        System.out.println(rf.readShort());
        System.out.println(rf.readUTF());
        rf.close();
    }
    public static void main(String[] args)
            throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
//        for (int i = 0; i < 7; i++)
//            rf.writeDouble(i * 1.414);
//        rf.writeUTF("The end of the file");
//        rf.close();
//        display();
//        rf = new RandomAccessFile(file, "rw");
//        rf.seek(5 * 8);
//        rf.writeDouble(47.0001);
//        rf.close();
//        display();


        rf.write(new byte[]{1,2,3,4}, 0, 4);
        rf.write(12345);
        rf.writeBoolean(true);
        rf.writeByte(123);
        rf.writeBytes("writeBytes");
        rf.writeChar(97);
        rf.writeChars("writeChars");
        rf.writeDouble(12.223);
        rf.writeFloat(23.245f);
        rf.writeInt(321);
        rf.writeLong(32144);
        rf.writeShort(3245);
        rf.writeUTF("writeUTF");
        rf.close();
        display1();

    }
} /* Output:
Value 0: 0.0
Value 1: 1.414
Value 2: 2.828
Value 3: 4.242
Value 4: 5.656
Value 5: 7.069999999999999
Value 6: 8.484
The end of the file
Value 0: 0.0
Value 1: 1.414
Value 2: 2.828
Value 3: 4.242
Value 4: 5.656
Value 5: 47.0001
Value 6: 8.484
The end of the file
*///:~
