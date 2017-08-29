package com.example.doun.chapter17containers;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by power on 2017/8/29,029.
 */

public class SimpleHashMap21<K, V> extends AbstractMap<K, V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) buckets[index] = new LinkedList<MapEntry<K, V>>();
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
        boolean found = false;
        int foundTimes = 0;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            foundTimes++;
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                System.out.println("发现冲突"+foundTimes+"   "+iPair);
                it.set(pair); /* Replace old with new*/
                found = true;
                break;
            }
        }
        if (!found) buckets[index].add(pair);
        return oldValue;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            return null;
        for (MapEntry<K, V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mpair : bucket) set.add(mpair);
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap21<String, String> m = new SimpleHashMap21<>();

//        m.putAll(Countries.capitals(5));
//        m.putAll(Countries.capitals(5));
//        m.put("sss", "ee");
//        m.put("BOTSWANA", Countries.capitals(5).get("BOTSWANA"));

        m.putAll(Countries.capitals());
        m.putAll(Countries.capitals(5));
        m.put("sss", "ee");
        m.put("BOTSWANA", Countries.capitals(5).get("BOTSWANA"));


        System.out.println(m);
        System.out.println(m.get("ERITREA"));
        System.out.println(m.size());
    }
}