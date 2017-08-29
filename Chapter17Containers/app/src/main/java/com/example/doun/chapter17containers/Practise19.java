package com.example.doun.chapter17containers;

import java.util.ArrayList;

/**
 * Created by power on 2017/8/29,029.
 */

public class Practise19 {

    private static void method(ArrayList<String> s) {
        //定义 一个容器
        SlowMap<String, Integer> tm = new SlowMap<>();

        //通过for循环逐一统计每个字符出现的次数
        for (int x = 0; x < s.size(); x++) {
            if (null == tm.get(s.get(x))) {
                tm.put(s.get(x), 1);
            } else {
                tm.put(s.get(x), tm.get(s.get(x)) + 1);
            }
        }
        //调用自定义方法在控制台上输出统计信息
        printMapDemo1(tm);

//        try{
//            savaXMLFile(tm);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }

    private static void printMapDemo1(SlowMap<String, Integer> tm) {
//        Set<String> st = tm.keySet();
//        Iterator<String> ti = st.iterator();
//        for (; ti.hasNext(); ) {
//            String key = ti.next();
//            System.out.println(key + "(" + tm.get(key) + ")");
//
//        }
        System.out.println(tm.toString());
    }


    public static void main(String[] args) {
        TextFile textFile = new TextFile("Practise13.java", "\\W+");
        method(textFile);

    }
}
