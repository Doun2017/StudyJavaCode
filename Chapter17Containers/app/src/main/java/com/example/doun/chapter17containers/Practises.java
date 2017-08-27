package com.example.doun.chapter17containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static com.example.doun.chapter17containers.Countries.*;

/**
 * Created by Doun on 2017/8/26.
 */

public class Practises {

    static void testList(String msg, List<String> list) {
        System.out.println("--- " + msg + " ---");
        List<String> c = list;
        List<String> subList = list.subList(1, 8);
        // Copy of the sublist:
        List<String> c2 = new ArrayList<String>(subList);


        try {
            c.addAll(0, c2);
        } catch (Exception e) {
            System.out.println("addAll(): " + e);
        }
        try {
            c.set(0, "replaced");
        } catch (Exception e) {
            System.out.println("set(): " + e);
        }
        try {
            c.add(0, "addded");
        } catch (Exception e) {
            System.out.println("add(): " + e);
        }
        try {
            c.remove(0);
        } catch (Exception e) {
            System.out.println("remove(): " + e);
        }
//    default void replaceAll(UnaryOperator<E> operator) {
//        default void sort(Comparator<? super E> c) {Collections.sort(this, c);

    }
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
//        Map<String, String> all = capitals();
//        Map<String, String> allA = new HashMap<>();
//        Set<String> allNames = all.keySet();
//        Set<String> allNamesA = new HashSet<>();
//        for (String str : allNames){
//            if (str.charAt(0) == 'A'){
//                allNamesA.add(str);
//                allA.put(str, all.get(str));
//            }
//        }
//        System.out.println(allA);
//        System.out.println(allA.size());
//        System.out.println(allNamesA);
//        System.out.println(allNamesA.size());

        //practice 17.3
//        Set<String> hashSet = new HashSet<>();
//        Set<String> treeSet = new TreeSet<>();
//        Set<String> linkedHashSet = new LinkedHashSet<>();
//        for (int i=0; i<10; i++) {
//            hashSet.add(names(1).get(0));
//            treeSet.add(names(1).get(0));
//            linkedHashSet.add(names(1).get(0));
//        }
//        System.out.println(hashSet);
//        System.out.println(treeSet);
//        System.out.println(linkedHashSet);


        //practice 17.6
//        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
//        testList("Modifiable Copy", new ArrayList<String>(list));
//        testList("Arrays.asList()", list);
//        testList("unmodifiableList()", Collections.unmodifiableList(new ArrayList<String>(list)));

        //practice 17.7
//        List<String> arrayList = new ArrayList<>(names(10));
//        List<String> linkedList = new LinkedList<>(names(10));
//
//        Iterator<String> arrayIter = arrayList.iterator();
//        Iterator<String> linkedIter = linkedList.iterator();
//
//        while (arrayIter.hasNext()){
//            System.out.println(arrayIter.next());
//        }
//        System.out.println();
//
//        while (linkedIter.hasNext()){
//            System.out.println(linkedIter.next());
//        }
//        System.out.println();
//
//        ListIterator<String> linkedListIter = linkedList.listIterator();
//        int index=0;
//        while (linkedListIter.hasNext()){
//            ListIterator<String> arrayListIter = arrayList.listIterator(index);
//            arrayListIter.add(linkedListIter.next());
//            index += 2;
//        }
//        System.out.println(arrayList);
//
//        linkedListIter = linkedList.listIterator(linkedList.size());
//        index=0;
//        while (linkedListIter.hasPrevious()){
//            ListIterator<String> arrayListIter = arrayList.listIterator(index);
//            arrayListIter.add(linkedListIter.previous());
//            index += 2;
//        }
//        System.out.println(arrayList);

        //practice 17.9
        RandomGenerator.String RS = new RandomGenerator.String(8);
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i=0; i<10; i++)
            treeSet.add(RS.next());
        System.out.println(treeSet);


    }

}
