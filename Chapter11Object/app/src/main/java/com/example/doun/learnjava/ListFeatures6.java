package com.example.doun.learnjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by power on 2017/2/13,013.
 */

public class ListFeatures6 {
    public String stringout() {
        String outstr = null;
        Random rand = new Random(47);
        List<String> pets = new ArrayList<String>(Arrays.asList("Rat","Manx","Cymric","Mutt","Pug","Cymric","Pug"));

        outstr = ("1: " + pets);
        String h = new String("aaa");
        pets.add(h); // Automatically resizes
        outstr+=("\n");
        outstr+=("2: " + pets);
        outstr+=("\n");
        outstr+=("3: " + pets.contains(h));
        pets.remove(h); // Remove by object
        String p = pets.get(2);
        outstr+=("\n");
        outstr+=("4: " +  p + " " + pets.indexOf(p));
        String cymric = new String("bbb");
        outstr+=("\n");
        outstr+=("5: " + pets.indexOf(cymric));
        outstr+=("\n");
        outstr+=("6: " + pets.remove(cymric));
        // Must be the exact object:
        outstr+=("\n");
        outstr+=("7: " + pets.remove(p));
        outstr+=("\n");
        outstr+=("8: " + pets);
        pets.add(3, new String("ccc")); // Insert at an index
        outstr+=("\n");
        outstr+=("9: " + pets);
        List<String> sub = pets.subList(1, 4);
        outstr+=("subList: " + sub);
        outstr+=("\n");
        outstr+=("10: " + pets.containsAll(sub));
        Collections.sort(sub); // In-place sort
        outstr+=("sorted subList: " + sub);
        // Order is not important in containsAll():
        outstr+=("\n");
        outstr+=("11: " + pets.containsAll(sub));

        Collections.shuffle(sub, rand); // Mix it up
        outstr+=("shuffled subList: " + sub);
        outstr+=("\n");
        outstr+=("12: " + pets.containsAll(sub));
        List<String> copy = new ArrayList<String>(pets);
        sub = Arrays.asList(pets.get(1), pets.get(4));
        outstr+=("sub: " + sub);
        copy.retainAll(sub);
        outstr+=("\n");
        outstr+=("13: " + copy);
        copy = new ArrayList<String>(pets); // Get a fresh copy
        copy.remove(2); // Remove by index
        outstr+=("\n");
        outstr+=("14: " + copy);
        copy.removeAll(sub); // Only removes exact objects
        outstr+=("\n");
        outstr+=("15: " + copy);
        copy.set(1, new String("ddd")); // Replace an element
        outstr+=("\n");
        outstr+=("16: " + copy);
        copy.addAll(2, sub); // Insert a list in the middle
        outstr+=("\n");
        outstr+=("17: " + copy);
        outstr+=("\n");
        outstr+=("18: " + pets.isEmpty());
        pets.clear(); // Remove all elements
        outstr+=("\n");
        outstr+=("19: " + pets);
        outstr+=("\n");
        outstr+=("20: " + pets.isEmpty());
        pets.addAll(new ArrayList<String>(Arrays.asList("Rat","Manx","Cymric","Mutt")));
        outstr+=("\n");
        outstr+=("21: " + pets);
        Object[] o = pets.toArray();
        outstr+=("\n");
        outstr+=("22: " + o[3]);
        String[] pa = pets.toArray(new String[0]);
        outstr+=("\n");
        outstr+=("23: " + pa[3]);

        return outstr;
    }
} /* Output:
1: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug]
2: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Hamster]
3: true
4: Cymric 2
5: -1
6: false
7: true
8: [Rat, Manx, Mutt, Pug, Cymric, Pug]
9: [Rat, Manx, Mutt, Mouse, Pug, Cymric, Pug]
subList: [Manx, Mutt, Mouse]
10: true
sorted subList: [Manx, Mouse, Mutt]
11: true
shuffled subList: [Mouse, Manx, Mutt]
12: true
sub: [Mouse, Pug]
13: [Mouse, Pug]
14: [Rat, Mouse, Mutt, Pug, Cymric, Pug]
15: [Rat, Mutt, Cymric, Pug]
16: [Rat, Mouse, Cymric, Pug]
17: [Rat, Mouse, Mouse, Pug, Cymric, Pug]
18: false
19: []
20: true
21: [Manx, Cymric, Rat, EgyptianMau]
22: EgyptianMau
23: 14
*///:~