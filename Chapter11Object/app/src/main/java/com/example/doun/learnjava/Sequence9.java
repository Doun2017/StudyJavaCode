package com.example.doun.learnjava;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by power on 2017/2/14,014.
 */

public class Sequence9 {
    private ArrayList items;
    private int next = 0;
    public Sequence9() { items = new ArrayList(); }
    public void add(Object x) {
        items.add(x);
    }

    public Iterator selector() {
        return items.iterator();
    }
}
