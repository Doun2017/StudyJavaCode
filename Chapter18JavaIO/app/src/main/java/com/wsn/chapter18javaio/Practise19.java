package com.wsn.chapter18javaio;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Doun on 2017/8/17.
 */

public class Practise19 {


    // Simple test:
    public static void main(String[] args) {
        try {
            byte[] b = BinaryFile.read("Practise19.java");
            count(b);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    static void count(byte[] b) {
        TreeMap<Byte, Integer> ll = new TreeMap<Byte, Integer>();
        for (int ii = 0; ii < b.length; ii++) {
            Integer i = ll.get(b[ii]);
            ll.put(b[ii], i == null ? 1 : i + 1);
        }
        Set s = ll.entrySet();
        Collection c = ll.values();
        Object[] key = c.toArray();
        Arrays.sort(key);
        int max = (Integer) key[key.length - 1];
        for (; max > 0; max--) {
            Iterator it = s.iterator();
            while (it.hasNext()) {
                Map.Entry me = (Map.Entry) it.next();
                if (max == (Integer) me.getValue()) {
                    System.out.println("值：" + me.getKey() + "出现次数：" + me.getValue());
//                    Print.p("值：" + me.getKey() + "出现次数：" + me.getValue());
                }
            }
        }
    }
}
