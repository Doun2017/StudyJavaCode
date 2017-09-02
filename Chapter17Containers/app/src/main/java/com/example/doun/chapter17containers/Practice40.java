package com.example.doun.chapter17containers;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Doun on 2017/9/2.
 */

public class Practice40 implements Comparable<Practice40> {
    String stringOne;
    String stringTwo;

    @Override
    public int compareTo(@NonNull Practice40 o) {
        return stringOne.compareTo(o.stringOne);
    }

    public Practice40(String stringOne, String stringTwo) {
        this.stringOne = stringOne;
        this.stringTwo = stringTwo;
    }

    @Override
    public String toString() {
        return "stringOne:"+stringOne+"   stringTwo:"+stringTwo+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Practice40 that = (Practice40) o;

        if (!stringOne.equals(that.stringOne)) return false;
        return stringTwo.equals(that.stringTwo);

    }

    @Override
    public int hashCode() {
        int result = stringOne.hashCode();
        result = 31 * result + stringTwo.hashCode();
        return result;
    }

    public static void main(String[] args) {

         /* Practice40[] practice40s = new Practice40[10];
        RandomGenerator.String stringGen = new RandomGenerator.String();
//        for (Practice40 p:practice40s){
//            p = new Practice40(stringGen.next(), stringGen.next());
//        }
        ArrayList<Practice40> practice40ArrayList = new ArrayList<>();
        for (int i=0;i<10;i++) {
            practice40s[i] = new Practice40(stringGen.next(), stringGen.next());
            practice40ArrayList.add(new Practice40(stringGen.next(), stringGen.next()));
        }
        //排序前
        System.out.println("排序前");
        System.out.println(Arrays.toString(practice40s));
        System.out.println(practice40ArrayList);

        Arrays.sort(practice40s);
        Collections.sort(practice40ArrayList);

        //排序后
        System.out.println("排序后");
        System.out.println(Arrays.toString(practice40s));
        System.out.println(practice40ArrayList);

        for (int i=0;i<10;i++) {
            practice40s[i] = new Practice40(stringGen.next(), stringGen.next());
            practice40ArrayList.set(i, new Practice40(stringGen.next(), stringGen.next()));
        }
        //排序前
        System.out.println("Comparator排序前");
        System.out.println(Arrays.toString(practice40s));
        System.out.println(practice40ArrayList);

        Arrays.sort(practice40s, new Practice40Comparator());
        Collections.sort(practice40ArrayList, new Practice40Comparator());

        //排序后
        System.out.println("Comparator排序后");
        System.out.println(Arrays.toString(practice40s));
        System.out.println(practice40ArrayList);


        int findIndex = Collections.binarySearch(practice40ArrayList,
                new Practice40("PKNjWUl", "MJOTlae"),new Practice40Comparator());
        System.out.println("findIndex="+findIndex);
        */


        RandomGenerator.String stringGen = new RandomGenerator.String();
        HashSet<Practice40> practice40HashSet = new HashSet<>();
        for (int i=0;i<10;i++) {
            practice40HashSet.add(new Practice40(stringGen.next(), stringGen.next()));
        }
        System.out.println(practice40HashSet);
        System.out.println(practice40HashSet.contains(new Practice40("PKNjWUl", "MJOTlae")));
        System.out.println(practice40HashSet.contains(new Practice40("YNzbrny", "GcFOWZn")));

        HashMap<Practice40, String> practice40HashMap = new HashMap<>();
        for (int i=0;i<10;i++) {
            practice40HashMap.put(new Practice40(stringGen.next(), stringGen.next()), i+"");
        }
        System.out.println(practice40HashMap);

        System.out.println("get:TQzGSrL,"+"rcVwhKO  "+practice40HashMap.get(new Practice40("TQzGSrL", "rcVwhKO"))+"");
    }
}

class Practice40Comparator implements Comparator<Practice40>{
    @Override
    public int compare(Practice40 o1, Practice40 o2) {
        return o1.stringTwo.compareTo(o2.stringTwo);
    }
}
