package com.example.doun.chapter17containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.doun.chapter17containers.Countries.*;

/**
 * Created by Doun on 2017/8/26.
 */

public class Practises {

    public static void main(String[] args) {

        //practice 17.1
//        List<String> list = new ArrayList<>(names(6));
//        List<String> list1 = new LinkedList<>(names(6));
//        System.out.println("初始状态");
//        System.out.println(list);
//        System.out.println(list1);
//        Collections.sort(list);
//        Collections.sort(list1);
//        System.out.println("排序后");
//        System.out.println(list);
//        System.out.println(list1);
//        Collections.shuffle(list);
//        Collections.shuffle(list1);
//        System.out.println("打乱后");
//        System.out.println(list);
//        System.out.println(list1);

        //practice 17.2
        Map<String, String> all = capitals();
        Map<String, String> allA = new HashMap<>();
        Set<String> allNames = all.keySet();
        Set<String> allNamesA = new HashSet<>();
        for (String str : allNames){
            if (str.charAt(0) == 'A'){
                allNamesA.add(str);
                allA.put(str, all.get(str));
            }
        }
        System.out.println(allA);
        System.out.println(allA.size());
        System.out.println(allNamesA);
        System.out.println(allNamesA.size());



    }

}
