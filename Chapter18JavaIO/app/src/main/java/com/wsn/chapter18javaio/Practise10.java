package com.wsn.chapter18javaio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by power on 2017/8/11,011.
 */

public class Practise10 {

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
            if (list.getLast().contains("Practise10"))
                System.out.print(list.getLast() + "\n");
            list.removeLast();
        }
    }

    public static void main(String[] args) throws IOException {
        Practise10 p = new Practise10();
        if (args.length>0){
            p.read(args[0]);
            p.show();
        }
    }
}
