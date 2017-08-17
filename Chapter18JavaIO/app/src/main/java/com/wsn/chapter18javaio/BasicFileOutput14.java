package com.wsn.chapter18javaio;

import android.os.SystemClock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Created by power on 2017/8/17,017.
 */

public class BasicFileOutput14 {
    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        LineNumberReader in = new LineNumberReader(new StringReader(BufferedInputFile.read("BasicFileOutput.java")));
        System.out.println("begin:"+ System.currentTimeMillis());
        PrintWriter out = new PrintWriter(new FileWriter(file));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null){
            out.println(lineCount++ + ": " + s);
            out.println(in.getLineNumber() + ": " + s);
        }
        out.close();
        System.out.println("end  :"+ System.currentTimeMillis());
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}
