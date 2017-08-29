//: containers/SlowMap.java
// A Map implemented with ArrayLists.
package com.example.doun.chapter17containers;

import java.util.*;

//import net.mindview.util.*;

public class SlowMap<K, V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();

    public V put(K key, V value) {
        V oldValue = get(key); // The old value or null
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else
            values.set(keys.indexOf(key), value);
        return oldValue;
    }

    public V get(Object key) { // key is type Object, not K
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    public V remove(Object key) {
        if (!keys.contains(key))
            return null;
        V oldvalue = values.get(keys.indexOf(key));
        values.remove(keys.indexOf(key));
        keys.remove(key);
        return oldvalue;
    }

    public void clear() {
        keys.clear();
        values.clear();
    }

//    public Set<Map.Entry<K, V>> entrySet() {
//        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
//        Iterator<K> ki = keys.iterator();
//        Iterator<V> vi = values.iterator();
//        while (ki.hasNext())
//            set.add(new MapEntry<K, V>(ki.next(), vi.next()));
//        return set;
//    }
    private EntrySet entrySet = new EntrySet();
    @Override public Set<Map.Entry<K,V>> entrySet() {
        return entrySet;
    }
    // Uses the 'Flyweight' pattern
    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new Iterator<Map.Entry<K, V>>() {
                private int index = -1;
                boolean canRemove;

                public boolean hasNext() {
                    return index < keys.size() - 1;
                }

                public Map.Entry<K, V> next() {
                    canRemove = true;
                    ++index;
                    return new MapEntry<K, V>(keys.get(index), values.get(index));
                }

                public void remove() {
                    if (!canRemove) throw new IllegalStateException();
                    canRemove = false;
                    keys.remove(index);
                    values.remove(index--);
                }
            };
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean contains(Object o) {
            if (o instanceof MapEntry) {
                MapEntry<K, V> e = (MapEntry<K, V>) o;
                K key = e.getKey();
                if (keys.contains(key))
                    return e.equals(
                            new MapEntry<K, V>(key, get(key)));
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean remove(Object o) {
            if (contains(o)) {
                MapEntry<K, V> e = (MapEntry<K, V>) o;
                K key = e.getKey();
                V val = e.getValue();
                keys.remove(key);
                values.remove(val);
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            return keys.size();
        }

        @Override
        public void clear() {
            keys.clear();
            values.clear();
        }
    }


    public static void main(String[] args) {
//        SlowMap<String, String> m = new SlowMap<String, String>();
//        m.putAll(Countries.capitals(15));
//        System.out.println(m);
//        System.out.println(m.get("BULGARIA"));
//        System.out.println(m.entrySet());


        SlowMap<Object, Object> map = new SlowMap();
        Maps.test(map);
    }
} /* Output:
{CAMEROON=Yaounde, CHAD=N'djamena, CONGO=Brazzaville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, CENTRAL AFRICAN REPUBLIC=Bangui, BOTSWANA=Gaberone, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, DJIBOUTI=Dijibouti}
Sofia
[CAMEROON=Yaounde, CHAD=N'djamena, CONGO=Brazzaville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, CENTRAL AFRICAN REPUBLIC=Bangui, BOTSWANA=Gaberone, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, DJIBOUTI=Dijibouti]
*///:~
