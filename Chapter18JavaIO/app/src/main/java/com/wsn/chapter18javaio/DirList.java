//: io/DirList.java
// Display a directory listing using regular expressions.
// {Args: "D.*\.java"}
package com.wsn.chapter18javaio;

import java.util.regex.*;
import java.io.*;
import java.util.*;

import static com.wsn.chapter18javaio.TextFile.*;


public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        //练习18.1
        for (String dirItem : list) {
            String file = read(dirItem);
            if (file.contains("test"))//查看文件内容中是否包含此单词
                System.out.println(dirItem);
        }
        //练习18.3
        int sizeSum = 0;
        for (String dirItem : list) {
            String file = read(dirItem);
            sizeSum += file.length();//将目录中所有文件的长度相加
            System.out.println(sizeSum + "");
        }

    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
} /* Output:
DirectoryDemo.java
DirList.java
DirList2.java
DirList3.java
*///:~
