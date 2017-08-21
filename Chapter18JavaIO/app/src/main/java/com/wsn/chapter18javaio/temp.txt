package com.wsn.chapter18javaio;

/**
 * Created by Doun on 2017/8/21.
 */

import java.nio.*;
import java.nio.channels.*;
import java.io.*;
import java.nio.charset.*;

abstract class CompareAllocations {
    private String name;
    protected ByteBuffer buffer;
    private int size;

    public CompareAllocations(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void runComparison() {
        System.out.println("Program Name: <" + name + ">");
        try {
            long startTime = System.nanoTime();
            directAllocate();
            long endTime = System.nanoTime();
            System.out.println(
                    "Direct Allocation Cost for buffer of size: <"
                            + size + "> is <" + (endTime - startTime) + ">");
            startTime = System.nanoTime();
            execute();
            endTime = System.nanoTime();
            System.out.println(
                    "Execution cost using direct buffer: <"
                            + (endTime - startTime) + ">");
            startTime = System.nanoTime();
            indirectAllocate();
            endTime = System.nanoTime();
            System.out.println(
                    "Indirect Allocation Cost for buffer of size: <"
                            + size + "> is <" + (endTime - startTime) + ">");
            startTime = System.nanoTime();
            execute();
            endTime = System.nanoTime();
            System.out.println(
                    "Execution cost using indirect buffer: <"
                            + (endTime - startTime) + ">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void directAllocate() {
        buffer = ByteBuffer.allocateDirect(size);
    }

    public abstract void execute() throws IOException;

    public void indirectAllocate() {
        buffer = ByteBuffer.allocate(size);
    }
}

public class Practise25 {
    public static void main(String[] args) {
        CompareAllocations[] comparisons = {
                new CompareAllocations("GetChannel", 8192) {
                    public void execute() throws IOException {
                        FileChannel fc =
                                new FileInputStream("Practise25.java")
                                        .getChannel();
                        fc.read(buffer);
                        buffer.flip();
                        while (buffer.hasRemaining())
                            buffer.get();
                    }
                },
                new CompareAllocations("ChannelCopy", 16384) {
                    public void execute() throws IOException {
                        FileChannel in =
                                new FileInputStream("Practise25.java")
                                        .getChannel(),
                                out = new FileOutputStream("temp.txt")
                                        .getChannel();
                        while (in.read(buffer) != -1) {
                            buffer.flip(); // Prepare for writing
                            out.write(buffer);
                            buffer.clear(); // Prepare for reading
                        }
                    }
                },
                new CompareAllocations("BufferToText", 8192) {
                    public void execute() throws IOException {
                        FileChannel fc =
                                new FileOutputStream("data2.txt")
                                        .getChannel();
                        fc.write(ByteBuffer.wrap("Some text".getBytes()));
                        fc.close();
                        fc = new FileInputStream("data2.txt")
                                .getChannel();


                        fc.read(buffer);
                        buffer.flip();
                        buffer.asCharBuffer().toString();
// Decode using this system's default Charset:
                        buffer.rewind();
                        Charset.forName(
                                System.getProperty("file.encoding"))
                                .decode(buffer);
                        fc = new FileOutputStream("data2.txt")
                                .getChannel();
                        fc.write(ByteBuffer.wrap(
                                "Some text".getBytes("UTF-16BE")));
                        fc.close();
// Now try reading again:
                        fc = new FileInputStream("data2.txt")
                                .getChannel();
                        buffer.clear();
                        fc.read(buffer);
                        buffer.flip();
                        buffer.asCharBuffer().toString();
// Use a CharBuffer to write through:
                        fc = new FileOutputStream("data2.txt")
                                .getChannel();
                        buffer.clear();
                        buffer.asCharBuffer().put("Some text");
                        fc.write(buffer);
                        fc.close();
// Read and display:
                        fc = new FileInputStream("data2.txt")
                                .getChannel();
                        buffer.clear();
                        fc.read(buffer);
                        buffer.flip();
                        buffer.asCharBuffer().toString();
                    }
                },
                new CompareAllocations("GetData", 1024) {
                    public void execute() throws IOException {
// Store and read a char array:
                        buffer.asCharBuffer().put("Howdy!");
// Store and read a short:
                        buffer.asShortBuffer().put((short) 471142);
                        buffer.getShort();
                        buffer.rewind();
// Store and read an int:
                        buffer.asIntBuffer().put(99471142);
                        buffer.getInt();
                        buffer.rewind();
// Store and read a long:
                        buffer.asLongBuffer().put(99471142);
                        buffer.getLong();
                        buffer.rewind();
// Store and read a float:
                        buffer.asFloatBuffer().put(99471142);
                        buffer.getFloat();
                        buffer.rewind();
// Store and read a double:
                        buffer.asDoubleBuffer().put(99471142);
                        buffer.getDouble();
                        buffer.rewind();
                    }
                },
                new CompareAllocations("IntBufferDemo", 1024) {
                    public void execute() throws IOException {
                        IntBuffer ib = buffer.asIntBuffer();
// Store an array of int:
                        ib.put(
                                new int[]{11, 42, 47, 99, 143, 811, 1016});
// Absolute location read and write:
                        ib.get(3);
                        ib.put(3, 1811);
                        ib.flip();
                        while (ib.hasRemaining()) {
                            int i = ib.get();
                        }
                    }
                },
                new CompareAllocations("UsingBuffers", 32) {
                    public void execute() throws IOException {
                        char[] data = "UsingBuffers".toCharArray();
                        CharBuffer cb = buffer.asCharBuffer();
                        cb.put(data);
                        cb.rewind();
                        symmetricScramble(cb);
                        cb.rewind();
                        symmetricScramble(cb);
                        cb.rewind();
                    }

                    private void symmetricScramble(CharBuffer buffer) {
                        while (buffer.hasRemaining()) {
                            buffer.mark();
                            char c1 = buffer.get();
                            char c2 = buffer.get();
                            buffer.reset();
                            buffer.put(c2).put(c1);
                        }
                    }
                }
        };
        for (int i = 0; i < comparisons.length; i++)
            comparisons[i].runComparison();
    }


}
