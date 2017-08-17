//: io/StoringAndRecoveringData.java
package com.wsn.chapter18javaio;

import java.io.*;
import java.util.Arrays;

public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");

        out.write(new byte[]{1,2,3,4}, 0, 4);
        out.write(12345);
        out.writeBoolean(true);
        out.writeByte(123);
        out.writeBytes("writeBytes");
        out.writeChar(97);
        out.writeChars("writeChars");
        out.writeDouble(12.223);
        out.writeFloat(23.245f);
        out.writeInt(321);
        out.writeLong(32144);
        out.writeShort(3245);
        out.writeUTF("writeUTF");

        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
        System.out.println(in.readDouble());
        // Only readUTF() will recover the
        // Java-UTF String properly:
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        byte b1[] = new byte[10];
        byte b2[] = new byte[10];
        char c3[] = new char[10];

        System.out.println(in.read(b2, 0, 4));
        System.out.println(Arrays.toString(b2));
        System.out.println(in.readByte());
        System.out.println(in.readBoolean());
        System.out.println(in.readByte());
        in.readFully(b1);
        System.out.println(Arrays.toString(b1));
        System.out.println(in.readChar());
        for(int i = 0;i<10;i++){
            c3[i] = in.readChar();
        }
        System.out.println(Arrays.toString(c3));

//        while(in.available()>0)
//        {
//            // read character
//            char c = in.readChar();
//            System.out.print(c);
//        }
        System.out.println(in.readDouble());
        System.out.println(in.readFloat());
        System.out.println(in.readInt());
        System.out.println(in.readLong());
        System.out.println(in.readShort());
        System.out.println(in.readUTF());

    }
} /* Output:
3.14159
That was pi
1.41413
Square root of 2
*///:~
