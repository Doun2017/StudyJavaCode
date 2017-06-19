package com.example.doun.learnjava;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by power on 2017/2/16,016.
 */

public class CollectionSequence30 implements Collection<Gerbil> {
//    private Gerbil[] pets;
    private ArrayList<Gerbil> pets;

    public CollectionSequence30(ArrayList<Gerbil> pets) {
        this.pets = pets;
    }
    public CollectionSequence30() {
        this.pets = new ArrayList<>();
    }

    @Override
    public int size() {
        return pets.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return pets.remove(o);
    }

    @NonNull
    @Override
    public Iterator<Gerbil> iterator() {
        return pets.iterator();
//        return new Iterator<Gerbil>() {
//            private int index = 0;
//            public boolean hasNext() {
//                return index < pets.toArray().length;
//            }
//            public Gerbil next() { return pets.get(index++); }
//            public void remove() { // Not implemented
//                throw new UnsupportedOperationException();
//            }
//        };
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return pets.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] a) {
        return pets.toArray(a);
    }

    @Override
    public boolean add(Gerbil gerbil) {
        return pets.add(gerbil);
    }

    @Override
    public boolean remove(Object o) {
        return pets.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return pets.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Gerbil> c) {
        return pets.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return pets.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return pets.retainAll(c);
    }

    @Override
    public void clear() {
        pets.clear();
    }


}
