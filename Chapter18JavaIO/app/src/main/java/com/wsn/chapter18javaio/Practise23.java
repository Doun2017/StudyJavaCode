package com.wsn.chapter18javaio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.BitSet;

/**
 * Created by Doun on 2017/8/21.
 *
 * 23
 * Create and test a utility method to print the contents
 * of a CharBuffer up to the point where the characters
 * are no longer printable.
 *
 * 从习题答案抄的，看完答案才理解题意。。。
 */

public class Practise23 {
    static BitSet isPrintable = new BitSet(127);
    static String encoding =
            System.getProperty("file.encoding");

    static {
// Assume an encoding that obeys ASCII eg.ISO-8859-1.
// Characters 32 to 127 represent printable characters.
        for (int i = 32; i <= 127; i++)
            isPrintable.set(i);
    }

    // Set the position to the last printable character
    public static void setPrintableLimit(CharBuffer cb) {
        cb.rewind();
        while (isPrintable.get(cb.get())) ;
        cb.limit(cb.position() - 1);
        cb.rewind();
    }

    public static void main(String[] args) {
        System.out.println("Default Encoding is: " + encoding);
        CharBuffer buffer =
                ByteBuffer.allocate(16).asCharBuffer();
        buffer.put("ABCDE" + (char) 0x01 + "FG");
        buffer.rewind();
        System.out.println(buffer); // Print everything
        setPrintableLimit(buffer);
        System.out.println(buffer); // Print printable
    }

}
