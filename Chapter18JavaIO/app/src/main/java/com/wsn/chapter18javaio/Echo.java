//: io/Echo.java
// How to read from standard input.
// {RunByHand}
package com.wsn.chapter18javaio;
import java.io.*;
public class Echo {
    public static void main(String[] args) throws IOException {
//        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader stdin = new BufferedReader( new InputStreamReader(new FileInputStream(new File("Echo.java"))));
        String s;
        while ((s = stdin.readLine()) != null && s.length() != 0)
            System.out.println(s.toUpperCase());
        // An empty line or Ctrl-Z terminates the program
    }
} ///:~
