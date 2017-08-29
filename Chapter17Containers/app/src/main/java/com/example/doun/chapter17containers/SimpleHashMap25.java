//: containers/SimpleHashMap25.java
// A demonstration hashed Map.
package com.example.doun.chapter17containers;

import java.util.*;
//import net.mindview.util.*;

public class SimpleHashMap25<K, V> extends AbstractMap<K, V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    MapEntry<K, V>[] buckets = new MapEntry[SIZE];

    @Override
    public void clear() {
        for (MapEntry<K, V> linkedList : buckets) {
            linkedList = null;
        }
    }

    @Override
    public V remove(Object key) {
        if (get(key) != null) {
            K k = (K) key;
            int index = Math.abs(k.hashCode()) % SIZE;
            if (buckets[index] == null) return null;
            MapEntry<K,V> mapEntry = buckets[index];
            MapEntry<K,V> premapEntry = null;
            while (mapEntry != null){
                if (mapEntry.getKey().equals(key)) {
                    if (premapEntry !=null)
                        premapEntry.setlast(mapEntry.getlast());
                    return mapEntry.getValue();
                }
                premapEntry = mapEntry;
                mapEntry = mapEntry.getlast();
            }
        }
        return null;
//        return super.remove(key);
    }

    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new MapEntry(key, value);
        else {
            MapEntry<K,V> mapEntry = buckets[index];
            boolean found = false;
            while (mapEntry != null){
                if (mapEntry.getKey().equals(key)) {
                    oldValue = mapEntry.getValue();
                    mapEntry.setValue(value);
//                    it.set(mapEntry);
                    found = true;/* Replace old with new*/
                    break;
                }
                mapEntry = mapEntry.getlast();
            }

            if (!found) buckets[index] = new MapEntry(buckets[index], key, value);
        }
        return oldValue;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        MapEntry<K,V> mapEntry = buckets[index];
        while (mapEntry != null){
            if (mapEntry.getKey().equals(key)) {
                return mapEntry.getValue();
            }
            mapEntry = mapEntry.getlast();
        }
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        for (MapEntry<K, V> mapEntry : buckets) {
            if (mapEntry == null) continue;
            while (mapEntry != null){
                set.add(mapEntry);
                mapEntry = mapEntry.getlast();
            }
        }
        return set;
    }


//    private EntrySet entrySet = new EntrySet();
//    @Override public Set<Map.Entry<K,V>> entrySet() {
//        return entrySet;
//    }
//    // Uses the 'Flyweight' pattern
//    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
//        @Override
//        public Iterator<Map.Entry<K, V>> iterator() {
//            return new Iterator<Map.Entry<K, V>>() {
//                private int index = -1;
//                boolean canRemove;
//
//                public boolean hasNext() {
//                    return index < size() - 1;
//                }
//
//                public Map.Entry<K, V> next() {
//                    canRemove = true;
//                    ++index;
//                    return new MapEntry<K, V>(keys.get(index), values.get(index));
//                }
//
//                public void remove() {
//                    if (!canRemove) throw new IllegalStateException();
//                    canRemove = false;
//                    keys.remove(index);
//                    values.remove(index--);
//                }
//            };
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        public boolean contains(Object o) {
//            if (o instanceof MapEntry) {
//                MapEntry<K, V> e = (MapEntry<K, V>) o;
//                int index = Math.abs(e.getKey().hashCode()) % SIZE;
//                if (buckets[index] == null) return false;
//                for (MapEntry<K, V> iPair : buckets[index])
//                    if (iPair.getKey().equals(e.getKey()))
//                        return true;
//            }
//            return false;
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        public boolean remove(Object o) {
//            if (contains(o)) {
//                MapEntry<K, V> e = (MapEntry<K, V>) o;
//
//                int index = Math.abs(e.getKey().hashCode()) % SIZE;
//                if (buckets[index] == null) return false;
//                for (MapEntry<K, V> iPair : buckets[index])
//                    if (iPair.getKey().equals(e.getKey())) {
//                        buckets[index].remove(e);
//                        return true;
//                    }
//
////                for (LinkedList<MapEntry<K, V>> linkedList: buckets){
////                    for (MapEntry<K,V> mapEntry:linkedList)
////                        if (mapEntry.equals(e))
////                            linkedList.remove(e);
//            }
//            return false;
//        }
//
//        @Override
//        public int size() {
//            int allsize = 0;
//            for (LinkedList<MapEntry<K, V>> linkedList: buckets){
//                if (linkedList != null)
//                    allsize += linkedList.size();
//            }
//            return allsize;
//        }
//
//        @Override
//        public void clear() {
//            for (LinkedList<MapEntry<K, V>> linkedList: buckets){
//                linkedList.clear();
//            }
//        }
//    }


    public int size() {
// Could rely on the inherited implementation, which
// returns entrySet().size(), but this is faster:
        int sz = 0;
        for (MapEntry<K, V> mapEntry : buckets){
            int subsz=0;
            while (mapEntry != null){
                subsz++;
                mapEntry = mapEntry.getlast();
            }
            sz += subsz;
        }
        return sz;
    }

    public boolean isEmpty() {
// Could just say return size() == 0;
// but this is faster:
        for (MapEntry<K, V> mapEntry : buckets)
            if (mapEntry != null)
                return false;
        return true;
    }

    public boolean containsKey(Object key) {
// A slight modification of the previous method:
        for (MapEntry<K, V> mapEntry : buckets) {
            if (mapEntry == null) continue;
            while (mapEntry != null){
                if (mapEntry.getKey().equals(key))
                    return true;
                mapEntry = mapEntry.getlast();
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        SimpleHashMap25<String, String> m = new SimpleHashMap25<>();
//        m.putAll(Countries.capitals(25));
//        System.out.println(m);
//        System.out.println(m.get("ERITREA"));
//        System.out.println(m.entrySet());


//        SimpleHashMap25<String, String> m = new SimpleHashMap25<String, String>();
//        m.putAll(Countries.capitals(10));
//        System.out.println(m);
//        System.out.println("m.get(\"BURUNDI\") = " + m.get("BURUNDI"));
//        m.remove("BURUNDI");
//        System.out.println("After removal, m.get(\"BURUNDI\") = " + m.get("BURUNDI"));
//        m.clear();
//        System.out.println("After clearing: " + m);

        SimpleHashMap25<String, String> m = new SimpleHashMap25<>(), m2 = new SimpleHashMap25<>();
        m.putAll(Countries.capitals(10));
        m2.putAll(Countries.capitals(10));
        System.out.println("m.size() = " + m.size());
        System.out.println("m.isEmpty() = " + m.isEmpty());
        System.out.println("m.equals(m2) = " + m.equals(m2));
        System.out.println("m.containsKey(\"BENIN\") = " + m.containsKey("BENIN"));
        System.out.println("m.containsKey(\"MARS\") = " + m.containsKey("MARS"));
        System.out.println("m.keySet() = " + m.keySet());
        System.out.println("m.values() = " + m.values());




    }
} /* Output:
{CAMEROON=Yaounde, CONGO=Brazzaville, CHAD=N'djamena, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, CENTRAL AFRICAN REPUBLIC=Bangui, GUINEA=Conakry, BOTSWANA=Gaberone, BISSAU=Bissau, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, ERITREA=Asmara, THE GAMBIA=Banjul, KENYA=Nairobi, GABON=Libreville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, EQUATORIAL GUINEA=Malabo, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, GHANA=Accra, DJIBOUTI=Dijibouti, ETHIOPIA=Addis Ababa}
Asmara
[CAMEROON=Yaounde, CONGO=Brazzaville, CHAD=N'djamena, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, CENTRAL AFRICAN REPUBLIC=Bangui, GUINEA=Conakry, BOTSWANA=Gaberone, BISSAU=Bissau, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, ERITREA=Asmara, THE GAMBIA=Banjul, KENYA=Nairobi, GABON=Libreville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, EQUATORIAL GUINEA=Malabo, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, GHANA=Accra, DJIBOUTI=Dijibouti, ETHIOPIA=Addis Ababa]
*///:~
