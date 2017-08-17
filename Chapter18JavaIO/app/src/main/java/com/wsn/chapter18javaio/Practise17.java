package com.wsn.chapter18javaio;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Created by Doun on 2017/8/17.
 */

public class Practise17 {

    private static void method(String s)
    {
        //定义 一个容器
        TreeMap<Character,Integer>  tm = new TreeMap<Character,Integer>();
        //将这TreeMap中的key全部取出来，然后储存到set集合中去
        Set<Character> st = tm.keySet();
        //将所需要统计的字符串转换成一个字符数组
        char[] c = s.toCharArray();
        //通过for循环逐一统计每个字符出现的次数
        for(int x=0;x<c.length;x++)
        {
            if(!st.contains(c[x]))
            {
                tm.put(c[x], 1);
            }
            else
            {
                tm.put(c[x], tm.get(c[x])+1);
            }
        }
        //调用自定义方法在控制台上输出统计信息
        printMapDemo(tm);
    }
    private static void printMapDemo(TreeMap<Character, Integer> tm) {
        // TODO Auto-generated method stub

        Set<Character> st = tm.keySet();
        Iterator<Character> ti = st.iterator();
        for(;ti.hasNext();)
        {
            char key = ti.next();
            System.out.println(key+"("+tm.get(key)+")");

        }
    }

    // Simple test:
    public static void main(String[] args) {
        String file = TextFile.read("Practise17.java");
        method(file);
    }
}
