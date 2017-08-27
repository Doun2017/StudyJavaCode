package com.example.doun.chapter17containers;

import android.support.annotation.NonNull;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Doun on 2017/8/27.
 */

class Item implements Comparable{
    private static Random r = new Random(47);

    private Integer value = r.nextInt(100);

    @Override
    public int compareTo(@NonNull Object o) {
        return value - ((Item)o).value;
    }

    @Override
    public String toString() {
        return value+"";
    }
}

public class Practice11 {

    public static void main(String[] args) {

        PriorityQueue<Item> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Item());
        priorityQueue.add(new Item());
        priorityQueue.add(new Item());
        priorityQueue.add(new Item());
        priorityQueue.add(new Item());
        System.out.println(priorityQueue.poll().toString());
        System.out.println(priorityQueue.poll().toString());
        System.out.println(priorityQueue.poll().toString());
        System.out.println(priorityQueue.poll().toString());
        System.out.println(priorityQueue.poll().toString());
    }
}
