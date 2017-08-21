//: strings/JGrep.java
// A very simple version of the "grep" program.
// {Args: JGrep.java "\\b[Ssct]\\w+"}
package com.wsn.chapter18javaio;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.*;

//import net.mindview.util.*;

public class JGrep {
    public static void main(String[] args) throws Exception {



        if (args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");



        MappedByteBuffer mappedByteBuffer = new FileInputStream(args[0]).getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, 1024);
        CharBuffer charBuffer = Charset.forName(System.getProperty("file.encoding")).decode(mappedByteBuffer);

        String strFile = charBuffer.toString();
        String[] strings = strFile.split("\n");

        //习题答案
//        FileChannel fc = new FileInputStream(args[0]).getChannel();
//        ByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
//        CharBuffer cb = Charset.forName(System.getProperty("file.encoding")).decode(buffer);
//        String[] strings = cb.toString().split("\n");


        for (String line : strings) {
            m.reset(line);
            while (m.find())
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
        }
//        for (String line : new TextFile(args[0])) {
//            m.reset(line);
//            while (m.find())
//                System.out.println(index++ + ": " + m.group() + ": " + m.start());
//        }
    }
} /* Output: (Sample)
0: strings: 4
1: simple: 10
2: the: 28
3: Ssct: 26
4: class: 7
5: static: 9
6: String: 26
7: throws: 41
8: System: 6
9: System: 6
10: compile: 24
11: through: 15
12: the: 23
13: the: 36
14: String: 8
15: System: 8
16: start: 31
*///:~
