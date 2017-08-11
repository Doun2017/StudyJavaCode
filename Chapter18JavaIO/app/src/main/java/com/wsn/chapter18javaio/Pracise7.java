package com.wsn.chapter18javaio;

/**
 * Created by power on 2017/8/11,011.
 */
import java.io.*;
import java.util.LinkedList;

public class Pracise7 {
    LinkedList<String> list = new LinkedList<>();

    // Throw exceptions to console:
    public void read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        while ((s = in.readLine()) != null)
            list.add(s);
        in.close();
    }
    public void show() {
        while (list.size()>0){
            System.out.print(list.getLast() + "\n");
            list.removeLast();
        }
    }

    public static void main(String[] args) throws IOException {
        Pracise7 p = new Pracise7();
        p.read("BufferedInputFile.java");
        p.show();
    }
}
