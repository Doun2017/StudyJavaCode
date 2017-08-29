//: containers/SimpleHashMap.java
// A demonstration hashed Map.
package com.example.doun.chapter17containers;

import java.util.*;
//import net.mindview.util.*;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public void clear() {
        for (LinkedList<MapEntry<K, V>> linkedList : buckets) {
            if (linkedList != null)
                linkedList.clear();
        }
    }

    @Override
    public V remove(Object key) {
        if (get(key) != null) {
            K k = (K) key;
            int index = Math.abs(k.hashCode()) % SIZE;
            if (buckets[index] == null) return null;
            for (MapEntry<K, V> iPair : buckets[index])
                if (iPair.getKey().equals(k)) {
                    buckets[index].remove(iPair);
                    V oldV = iPair.getValue();
                    return oldV;
                }
        }
        return null;
//        return super.remove(key);
    }

    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) buckets[index] = new LinkedList<MapEntry<K, V>>();
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
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
        if (buckets[index] == null) return null;
        for (MapEntry<K, V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mpair : bucket) set.add(mpair);
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
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
            if (bucket != null)
                sz += bucket.size();
        return sz;
    }

    public boolean isEmpty() {
// Could just say return size() == 0;
// but this is faster:
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
            if (bucket != null)
                return false;
        return true;
    }

    public boolean containsKey(Object key) {
// A slight modification of the previous method:
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mPair : bucket)
                if (mPair.getKey().equals(key))
                    return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        SimpleHashMap<String, String> m = new SimpleHashMap<>();
//        m.putAll(Countries.capitals(25));
//        System.out.println(m);
//        System.out.println(m.get("ERITREA"));
//        System.out.println(m.entrySet());


//        SimpleHashMap<String, String> m = new SimpleHashMap<String, String>();
//        m.putAll(Countries.capitals(10));
//        System.out.println(m);
//        System.out.println("m.get(\"BURUNDI\") = " + m.get("BURUNDI"));
//        m.remove("BURUNDI");
//        System.out.println("After removal, m.get(\"BURUNDI\") = " + m.get("BURUNDI"));
//        m.clear();
//        System.out.println("After clearing: " + m);

        SimpleHashMap<String, String> m = new SimpleHashMap<>(), m2 = new SimpleHashMap<>();
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
