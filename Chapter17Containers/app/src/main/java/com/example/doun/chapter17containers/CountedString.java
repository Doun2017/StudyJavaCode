//: containers/CountedString.java
// Creating a good hashCode().
package com.example.doun.chapter17containers;

import java.util.*;

//import static net.mindview.util.Print.*;

public class CountedString {
    private static List<String> created = new ArrayList<String>();
    private String s;
    private int id = 0;
    private char aChar = 0;

    public CountedString(String str) {
        s = str;
        if (s.length()>0)
            aChar = s.charAt(0);
        else
            aChar = 'a';
        created.add(s);
        // id is the total number of instances
        // of this string in use by CountedString:
        for (String s2 : created)
            if (s2.equals(s))
                id++;
    }

    public String toString() {
        return "String: " + s + " id: " + id + " aChar: " + aChar +
                " hashCode(): " + hashCode();
    }

    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id;
        // Using Joshua Bloch's recipe:
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        result = 37 * result + (int)aChar;
        return result;
    }

    public boolean equals(Object o) {
        return o instanceof CountedString &&
                s.equals(((CountedString) o).s) &&
                aChar == (((CountedString) o).aChar) &&
                id == ((CountedString) o).id;
    }

    public static void main(String[] args) {
        Map<CountedString, Integer> map = new HashMap<CountedString, Integer>();
        CountedString[] cs = new CountedString[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString("hi");
            map.put(cs[i], i); // Autobox int -> Integer
        }
        System.out.println(map);
        for (CountedString cstring : cs) {
            System.out.println("Looking up " + cstring);
            System.out.println(map.get(cstring));
        }
    }
} /* Output: (Sample)
{String: hi id: 4 hashCode(): 146450=3, String: hi id: 1 hashCode(): 146447=0, String: hi id: 3 hashCode(): 146449=2, String: hi id: 5 hashCode(): 146451=4, String: hi id: 2 hashCode(): 146448=1}
Looking up String: hi id: 1 hashCode(): 146447
0
Looking up String: hi id: 2 hashCode(): 146448
1
Looking up String: hi id: 3 hashCode(): 146449
2
Looking up String: hi id: 4 hashCode(): 146450
3
Looking up String: hi id: 5 hashCode(): 146451
4
*///:~
