package com.example.doun.chapter15generics;

import java.util.Arrays;
import java.util.List;

import static com.example.doun.chapter15generics.Functional.*;

/**
 * Created by power on 2017/7/21,021.
 */

class StringHolder{
    private String str;
    StringHolder(String str){
        this.str = str;
    }

    String getStr(){
        return str;
    }

    void setStr(String newStr){
        str = newStr;
    }

}


public class StringAdder implements Combiner<StringHolder> {
    public StringHolder combine(StringHolder x, StringHolder y) {
        String combineStr = x.getStr() + y.getStr();
        return new StringHolder(combineStr);
    }

    public static void main(String[] args) {
        // Generics, varargs & boxing working together:
        List<StringHolder> li = Arrays.asList(new StringHolder("a"),
                new StringHolder("b "),
                new StringHolder("c"),
                new StringHolder("d"),
                new StringHolder("e"),
                new StringHolder("f"));
        StringHolder result = reduce(li, new StringAdder());
        System.out.println(result.getStr());
    }

}