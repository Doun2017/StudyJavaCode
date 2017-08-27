package com.example.doun.chapter17containers;

/**
 * Created by Doun on 2017/8/27.
 */

//: containers/E10_CustomSortedSet.java
/****************** Exercise 10 *****************
 * Using a LinkedList as your underlying
 * implementation, define your own SortedSet.
 ***********************************************/

import java.util.*;

import static java.util.Collections.binarySearch;

class CustomSortedSet<T> implements SortedSet<T> {
    private final List<T> list;

    public CustomSortedSet() {
        list = new LinkedList<T>();
    }

    // If this sorted set is backed by an another one
    private CustomSortedSet(List<T> list) {
        this.list = list;
    }

    public String toString() {
        return list.toString();
    }

    /*** Methods defined in the Collection interface ***/
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        checkForNull(o);
        return
                binarySearch((List<Comparable<T>>) list, (T) o) >= 0;
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @SuppressWarnings("unchecked")
    public boolean add(T o) {
        checkForNull(o);
        int ip = binarySearch((List<Comparable<T>>) list, o);
        if (ip < 0) {
            ip = -(ip + 1);
            if (ip == list.size())
                list.add(o);
            else
                list.add(ip, o);
            return true;
        }
        return false;
    }

    public boolean remove(Object o) {
        checkForNull(o);
        return list.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        checkForNull(c);
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        checkForNull(c);
        checkForNullElements(c);
        boolean res = false;
        for (T item : c)
            res |= add(item);
        return res;
    }

    public boolean removeAll(Collection<?> c) {
        checkForNull(c);
        return list.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        checkForNull(c);
        return list.retainAll(c);
    }

    public void clear() {
        list.clear();
    }

    public boolean equals(Object o) {
        return o instanceof CustomSortedSet &&
                list.equals(((CustomSortedSet<?>) o).list);
    }

    public int hashCode() {
        return list.hashCode();
    }

    /*** Methods defined in the SortedSet interface ***/
    public Comparator<? super T> comparator() {
        return null;
    }

    public SortedSet<T> subSet(T fromElement, T toElement) {
        checkForNull(fromElement);
        checkForNull(toElement);
        int fromIndex = list.indexOf(fromElement);
        int toIndex = list.indexOf(toElement);
        checkForValidIndex(fromIndex);
        checkForValidIndex(toIndex);
        try {
            return new CustomSortedSet<T>(
                    list.subList(fromIndex, toIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public SortedSet<T> headSet(T toElement) {
        checkForNull(toElement);
        int toIndex = list.indexOf(toElement);
        checkForValidIndex(toIndex);
        try {
            return new CustomSortedSet<T>(
                    list.subList(0, toIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public SortedSet<T> tailSet(T fromElement) {
        checkForNull(fromElement);
        int fromIndex = list.indexOf(fromElement);
        checkForValidIndex(fromIndex);
        try {
            return new CustomSortedSet<T>(
                    list.subList(fromIndex, list.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public T first() {
        try {
            return list.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    public T last() {
        try {
            return list.get(list.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    /*** Utility methods ***/
    private void checkForNullElements(Collection<?> c) {
        for (Iterator<?> it = c.iterator(); it.hasNext(); )
            if (it.next() == null)
                throw new NullPointerException();
    }

    private void checkForNull(Object o) {
        if (o == null)
            throw new NullPointerException();
    }

    private void checkForValidIndex(int idx) {
        if (idx == -1)
            throw new IllegalArgumentException();
    }
}

public class E10_CustomSortedSet {
    // The whole main() method is basically copy-pasted from
// containers/SortedSetDemo.java!
    public static void main(String[] args) {
        SortedSet<String> sortedSet =
                new CustomSortedSet<String>();
        Collections.addAll(sortedSet,
                "one two three four five six seven eight"
                        .split(" "));
        System.out.println(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        System.out.println(low);
        System.out.println(high);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        System.out.println(low);
        System.out.println(high);
        System.out.println(sortedSet.subSet(low, high));
        System.out.println(sortedSet.headSet(high));
        System.out.println(sortedSet.tailSet(low));
        System.out.println(sortedSet.contains("three"));
        System.out.println(sortedSet.contains("eleven"));
        System.out.println(sortedSet.addAll(Arrays.asList(
                "three", "eleven")));
        System.out.println(sortedSet);
        System.out.println(sortedSet.retainAll(Arrays.asList(
                "three", "eleven")));
        System.out.println(sortedSet);
// Demonstrate data integrity
        try {
            sortedSet.addAll(Arrays.asList("zero", null));
        } catch (NullPointerException e) {
            System.out.println("Null elements not supported!");
        }
// The set will not contain "zero"!
        System.out.println(sortedSet);
    }
} /* Output:
[eight, five, four, one, seven, six, three, two]
eight
two
one
two
[one, seven, six, three]
[eight, five, four, one, seven, six, three]
[one, seven, six, three, two]
true
false
true
[eight, eleven, five, four, one, seven, six, three, two]
true
[eleven, three]
Null elements not supported!
[eleven, three]
*///:~