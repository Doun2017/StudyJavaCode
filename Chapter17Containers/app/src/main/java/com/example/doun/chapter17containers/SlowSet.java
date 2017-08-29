package com.example.doun.chapter17containers;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by power on 2017/8/29,029.
 */

public class SlowSet<V> extends AbstractSet<V> {
    private List<V> values = new ArrayList<V>();

    @Override
    public Iterator<V> iterator() {

        return values.listIterator();
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean add(V v) {
        if (!values.contains(v)) {
            values.add(v);
            return true;
        } else
            return true;
    }

//    @Override
//    public boolean addAll(Collection<? extends V> c) {
//        boolean modified = false;
//        for (V e : c)
//            if (add(e))
//                modified = true;
//        return modified;
////        return super.addAll(c);
//    }

    public static void main(String[] args) {
        SlowSet<String> m = new SlowSet<>();
        m.addAll(Countries.names(15));
        System.out.println(m);


    }
}
