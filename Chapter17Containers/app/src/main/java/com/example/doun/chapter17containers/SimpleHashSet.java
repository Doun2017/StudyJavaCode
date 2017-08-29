package com.example.doun.chapter17containers;

import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created by power on 2017/8/29,029.
 */

public class SimpleHashSet<V> extends AbstractSet<V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<V>[] buckets = new LinkedList[SIZE];

    protected transient int modCount = 0;

//
//    class SimpleHashSetIterator<K> implements Iterator<K> {
//        private int count;
//        private boolean canRemove;
//        private int index1, index2;
//        public boolean hasNext() { return count < size(); }
//        public K next() {
//            if(hasNext()) {
//                canRemove = true;
//                ++count;
//                for(;;) {
//                    // Position of the next non-empty bucket
//                    while(buckets[index1] == null)
//                        ++index1;
//                    // Position of the next item to return
//                    try {
//                        return buckets[index1].get(index2++);
//                    } catch(IndexOutOfBoundsException e) {
//                        // Continue search from the next bucket
//                        ++index1;
//                        index2 = 0;
//                    }
//                }
//            }
//            throw new NoSuchElementException();
//        }
//
////
////        public boolean hasNext() {
////            return cursor != size();
////        }
////
////        public V next() {
////            checkForComodification();
////            try {
////                int i = cursor;
////                V next = SimpleHashSet.this.get(i);
////                lastRet = i;
////                cursor = i + 1;
////                return next;
////            } catch (IndexOutOfBoundsException e) {
////                checkForComodification();
////                throw new NoSuchElementException();
////            }
////        }
////
////        public void remove() {
////            if (lastRet < 0)
////                throw new IllegalStateException();
////            checkForComodification();
////
////            try {
////                SimpleHashSet.this.remove(lastRet);
////                if (lastRet < cursor)
////                    cursor--;
////                lastRet = -1;
////                expectedModCount = modCount;
////            } catch (IndexOutOfBoundsException e) {
////                throw new ConcurrentModificationException();
////            }
////        }
////
////        final void checkForComodification() {
////            if (modCount != expectedModCount)
////                throw new ConcurrentModificationException();
////        }
//
////
////        V next;
////        int expectedModCount;   // For fast-fail
////        int index;              // current slot
////        V current;     // current entry
////
////        SimpleHashSetIterator() {
////            expectedModCount = size();
////            if (size > 0) { // advance to first entry
////                V[] t = table;
////                while (index < t.length && (next = t[index++]) == null)
////                    ;
////            }
////        }
////
////        @Override
////        public final boolean hasNext() {
////            return next != null;
////        }
////
////        @Override
////        final V nextEntry() {
////            if (modCount != expectedModCount)
////                throw new ConcurrentModificationException();
////            HashMapEntry<K,V> e = next;
////            if (e == null)
////                throw new NoSuchElementException();
////
////            if ((next = e.next) == null) {
////                HashMapEntry[] t = table;
////                while (index < t.length && (next = t[index++]) == null)
////                    ;
////            }
////            current = e;
////            return e;
////        }
////
////    public void remove() {
////        if (current == null)
////            throw new IllegalStateException();
////        if (modCount != expectedModCount)
////            throw new ConcurrentModificationException();
////        Object k = current.key;
////        current = null;
////        HashMap.this.removeEntryForKey(k);
////        expectedModCount = modCount;
////    }
////
////
//////            @Override
//////            public boolean hasNext() {
//////                return false;
//////            }
////
////    @Override
////    public V next() {
////        if (modCount != expectedModCount)
////            throw new ConcurrentModificationException();
////        HashMapEntry<K,V> e = next;
////        if (e == null)
////            throw new NoSuchElementException();
////
////        if ((next = e.next) == null) {
////            HashMapEntry[] t = table;
////            while (index < t.length && (next = t[index++]) == null)
////                ;
////        }
////        current = e;
////        return e;
////    }
//};


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int count;
            private boolean canRemove;
            private int index1, index2;

            public boolean hasNext() {
                return count < size();
            }

            public V next() {
                if (hasNext()) {
                    canRemove = true;
                    ++count;
                    for (; ; ) { /* Position of the next non-empty bucket*/
                        while (buckets[index1] == null) ++index1; /* Position of the next item to return*/
                        try {
                            return buckets[index1].get(index2++);
                        } catch (IndexOutOfBoundsException e) { /* Continue search from the next bucket*/
                            ++index1;
                            index2 = 0;
                        }
                    }
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                if (canRemove) {
                    canRemove = false;
                    buckets[index1].remove(--index2);
                    if (buckets[index1].isEmpty()) // Housekeeping...
                        buckets[index1++] = null;
                    --count;
                } else
                throw new IllegalStateException();
            }
        };
    }

    public boolean add(V key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null)
            buckets[index] = new LinkedList<V>();
        LinkedList<V> bucket = buckets[index];
        ListIterator<V> it = bucket.listIterator();
        while(it.hasNext())
            if(it.next().equals(key))
                return false;
// Sets do not permit duplicates and one
// was already in the set.
        it.add(key);
        return true;
    }
    public boolean contains(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null) return false;
        Iterator<V> it = buckets[index].iterator();
        while(it.hasNext())
            if(it.next().equals(key))
                return true;
        return false;
    }

    @Override
    public int size() {
        int sz = 0;
        for (LinkedList<V> bucket : buckets)
            if (bucket != null)
                sz += bucket.size();
        return sz;
    }




    public static void main(String[] args) {
        SimpleHashSet<String> m = new SimpleHashSet<String>();
        m.addAll(Countries.names(10));
        m.addAll(Countries.names(10));
        System.out.println("m = " + m);
        System.out.println("m.size() = " + m.size());
        Iterator<String> it = m.iterator();
        System.out.println("it.next()= "+ it.next());
        it.remove();
        System.out.println("it.next()= "+ it.next());
        System.out.println("m = " + m);
        m.remove("ALGERIA");
        System.out.println("m = " + m);
    }

}
