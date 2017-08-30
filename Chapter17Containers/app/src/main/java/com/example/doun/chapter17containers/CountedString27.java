package com.example.doun.chapter17containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by power on 2017/8/30,030.
 */

public class CountedString27 {

    private static List<String> created = new ArrayList<String>();
    private String s;
    private int id = 0;
    private char aChar = 0;

    public CountedString27(String str) {
        s = str;
        if (s.length()>0)
            aChar = s.charAt(0);
        else
            aChar = 'a';
        created.add(s);
        // id is the total number of instances
        // of this string in use by CountedString27:
        for (String s2 : created)
            if (s2.equals(s))
                id++;
    }

    public String toString() {
        return "String: " + s + " id: " + id + " aChar: " + aChar +
                " hashCode(): " + hashCode();
    }

    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id;
        // Using Joshua Bloch's recipe:
        int result = 17;
        result = 37 * result + s.hashCode();
//        result = 37 * result + id;
        result = 37 * result + (int)aChar;
        return result;
    }

    public boolean equals(Object o) {
        return o instanceof CountedString27 &&
                s.equals(((CountedString27) o).s) &&
                aChar == (((CountedString27) o).aChar) &&
                id == ((CountedString27) o).id;
    }

    public static void main(String[] args) {
        Map<CountedString27, Integer> map = new HashMap<CountedString27, Integer>();
        CountedString27[] cs = new CountedString27[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString27("hi");
            map.put(cs[i], i); // Autobox int -> Integer
        }
        System.out.println(map);
        for (CountedString27 cstring : cs) {
            System.out.println("Looking up " + cstring);
            System.out.println(map.get(cstring));
        }
    }

}
