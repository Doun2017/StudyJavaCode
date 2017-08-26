package com.example.doun.chapter17containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.example.doun.chapter17containers.Countries.*;

/**
 * Created by Doun on 2017/8/26.
 */

public class Practises {

    public static void main(String[] args) {

        //practice 17.1
        List<String> list = new ArrayList<>(names(6));
        List<String> list1 = new LinkedList<>(names(6));
        System.out.println("初始状态");
        System.out.println(list);
        System.out.println(list1);
        Collections.sort(list);
        Collections.sort(list1);
        System.out.println("排序后");
        System.out.println(list);
        System.out.println(list1);
        Collections.shuffle(list);
        Collections.shuffle(list1);
        System.out.println("打乱后");
        System.out.println(list);
        System.out.println(list1);




    }

}
