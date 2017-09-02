package com.example.doun.chapter17containers;

/**
 * Created by Doun on 2017/9/2.
 */

//: containers/E39_SimpleHashMapRehash.java
/****************** Exercise 39 *****************
 * Invoke a private rehash() method in SimpleHashMap when
 * the load factor exceeds 0.75. During rehash, determine
 * the new number of buckets by finding the first prime
 * number greater than twice the original number of buckets.
 ***********************************************/

import java.util.*;

@SuppressWarnings("unchecked")
class SimpleHashMap7<K, V> extends SimpleHashMap<K, V> {
    private int count; // Number of elements
    private static final double loadFactor = 0.75;
    // Use a prime initial capacity; the JDK uses a number,
    // which is a power of 2:
    private final static int initialCapacity = 11;
    private int capacity = initialCapacity;
    private int threshold = (int) (capacity * loadFactor);

    {
        buckets = new LinkedList[capacity];
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % capacity;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                found = true;
                break;
            }
        }
        if (!found) {
            if (count >= threshold)
                rehash();
            if (buckets[index] == null)
                buckets[index] = new LinkedList<MapEntry<K, V>>();
            buckets[index].add(pair);
            ++count;
        }
        return oldValue;
    }

    private boolean isPrime(int candidate) {
        for (int j = 2; j < candidate; j++)
            if (candidate % j == 0) return false;
        return true;
    }

    private int nextPrime(int candidate) {
        while (!isPrime(candidate))
            candidate++;
        return candidate;
    }

    private void rehash() {
        // Points to a new Set of the entries, so it
        // won't be bothered by what we're about to do:
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        // Get next prime capacity:
        capacity = nextPrime(capacity * 2);
        System.out.println("Rehashing, new capacity = " + capacity);
        buckets = new LinkedList[capacity];
        threshold = (int) (capacity * loadFactor);
        count = 0;
        // Fill new buckets (crude, but it works):
        while (it.hasNext()) {
            Map.Entry<K, V> me = it.next();
            put(me.getKey(), me.getValue());
        }
    }
}

public class E39_SimpleHashMapRehash {
    public static void main(String[] args) {
        SimpleHashMap7<String, String> m = new SimpleHashMap7<String, String>();
        m.putAll(Countries.capitals(50));
        System.out.println(m);
    }
} /* Output:
Rehashing, new capacity = 23
Rehashing, new capacity = 47
Rehashing, new capacity = 97
{CHAD=N'djamena, BISSAU=Bissau, MOROCCO=Rabat,
LIBERIA=Monrovia, GUINEA=Conakry, LESOTHO=Maseru,
BENIN=Porto-Novo, NAMIBIA=Windhoek, GABON=Libreville,
ETHIOPIA=Addis Ababa, SOUTH AFRICA=Pretoria/Cape Town, SAO
TOME E PRINCIPE=Sao Tome, COMOROS=Moroni, ANGOLA=Luanda,
CAPE VERDE=Praia, THE GAMBIA=Banjul,
MADAGASCAR=Antananarivo, CONGO=Brazzaville,
BURUNDI=Bujumbura, MALI=Bamako, DJIBOUTI=Dijibouti,
SOMALIA=Mogadishu, EQUATORIAL GUINEA=Malabo, SUDAN=Khartoum,
SWAZILAND=Mbabane, EGYPT=Cairo, GHANA=Accra, MAURITIUS=Port
Louis, CENTRAL AFRICAN REPUBLIC=Bangui, SEYCHELLES=Victoria,
COTE D'IVOIR (IVORY COAST)=Yamoussoukro, KENYA=Nairobi,
RWANDA=Kigali, ALGERIA=Algiers, BOTSWANA=Gaberone,
NIGER=Niamey, ERITREA=Asmara, LIBYA=Tripoli, TOGO=Lome,
NIGERIA=Abuja, MOZAMBIQUE=Maputo, MAURITANIA=Nouakchott,
BURKINA FASO=Ouagadougou, UGANDA=Kampala, SENEGAL=Dakar,
CAMEROON=Yaounde, TANZANIA=Dodoma, SIERRA LEONE=Freetown,
MALAWI=Lilongwe, TUNISIA=Tunis}
*///:~
