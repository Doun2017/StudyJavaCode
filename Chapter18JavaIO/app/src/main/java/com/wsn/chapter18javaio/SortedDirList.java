/**
 * Created by power on 2017/8/10,010.
 */
package com.wsn.chapter18javaio;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class SortedDirList {
    private String[] nameList;
    public SortedDirList(File path) {
        nameList = path.list();
    }

    public String[] list(){
        return nameList;
    }

    public String[] list(String regex){
        Pattern pattern;
        List<String> strings = new ArrayList<>();
        pattern = Pattern.compile(regex);
        for (String fileName:nameList){
            if (pattern.matcher(fileName).matches())
                strings.add(fileName);
        }
        String[] array =new String[strings.size()];
        return strings.toArray(array);
    }

    public static void main(String[] args) {

        SortedDirList sortedDirList = new SortedDirList(new File("."));
        System.out.println("all:"+Arrays.toString(sortedDirList.list()));
        System.out.println("picked:"+Arrays.toString(sortedDirList.list("TextFile.java")));

    }
}
