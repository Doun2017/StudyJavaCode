package com.wsn.chapter18javaio;

import com.wsn.chapter18javaio.xml.Person;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

/**
 * Created by power on 2017/8/23,023.
 */

public class Practice31 {
    private static void method(ArrayList<String> s) {
        //定义 一个容器
        TreeMap<String, Integer> tm = new TreeMap<>();
        //将这TreeMap中的key全部取出来，然后储存到set集合中去
        Set<String> st = tm.keySet();

        //通过for循环逐一统计每个字符出现的次数
        for (int x = 0; x < s.size(); x++) {
            if (!st.contains(s.get(x))) {
                tm.put(s.get(x), 1);
            } else {
                tm.put(s.get(x), tm.get(s.get(x)) + 1);
            }
        }
        //调用自定义方法在控制台上输出统计信息
        printMapDemo1(tm);
        try{
            savaXMLFile(tm);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void printMapDemo1(TreeMap<String, Integer> tm) {
        Set<String> st = tm.keySet();
        Iterator<String> ti = st.iterator();
        for (; ti.hasNext(); ) {
            String key = ti.next();
            System.out.println(key + "(" + tm.get(key) + ")");

        }
    }

    // Make it human-readable:
    public static void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer = new Serializer(os, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    private static void savaXMLFile(TreeMap<String, Integer> tm)  throws Exception{
        Element root = new Element("wordsCount");

        Set<String> st = tm.keySet();
        Iterator<String> ti = st.iterator();
        for (; ti.hasNext(); ) {
            String key = ti.next();
            System.out.println(key + "(" + tm.get(key) + ")");

            Element person = new Element("item");
            Element word = new Element("word");
            word.appendChild(key);
            Element times = new Element("times");
            times.appendChild(tm.get(key) + "");
            person.appendChild(word);
            person.appendChild(times);

            root.appendChild(person);
        }
        Document doc = new Document(root);
        format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream("wordsCount.xml")), doc);
    }

    private static void method(String s) {
        //定义 一个容器
        TreeMap<Character, Integer> tm = new TreeMap<Character, Integer>();
        //将这TreeMap中的key全部取出来，然后储存到set集合中去
        Set<Character> st = tm.keySet();
        //将所需要统计的字符串转换成一个字符数组
        char[] c = s.toCharArray();
        //通过for循环逐一统计每个字符出现的次数
        for (int x = 0; x < c.length; x++) {
            if (!st.contains(c[x])) {
                tm.put(c[x], 1);
            } else {
                tm.put(c[x], tm.get(c[x]) + 1);
            }
        }
        //调用自定义方法在控制台上输出统计信息
        printMapDemo(tm);
    }

    private static void printMapDemo(TreeMap<Character, Integer> tm) {
        // TODO Auto-generated method stub

        Set<Character> st = tm.keySet();
        Iterator<Character> ti = st.iterator();
        for (; ti.hasNext(); ) {
            char key = ti.next();
            System.out.println(key + "(" + tm.get(key) + ")");

        }
    }

    public static void main(String[] args) {
        TextFile textFile = new TextFile("Practice31.java", "\\W+");
        method(textFile);


//        String file = TextFile.read("Practise17.java");
//        method(file);
    }
}
