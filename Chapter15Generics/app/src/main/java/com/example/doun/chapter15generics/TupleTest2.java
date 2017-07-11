//: generics/TupleTest2.java
package com.example.doun.chapter15generics;


import static com.example.doun.chapter15generics.Tuple.*;

public class TupleTest2 {
    static TwoTuple<String, Integer> f() {
        return tuple("hi", 47);
    }

    static TwoTuple f2() {
        return tuple("hi", 47);
    }

    static ThreeTuple<String, String, Integer> g() {
        return tuple(new String(), "hi", 47);
    }

    static FourTuple<String, String, String, Integer> h() {
        return tuple(new String(), new String(), "hi", 47);
    }

    static FiveTuple<String, String, String, Integer, Double> k() {
        return tuple(new String(), new String(),
                "hi", 47, 11.1);
    }

    static SixTuple<String, String, String, String, Integer, Double> l() {
        return tuple(new String(), new String(),new String(), "hi", 47, 11.1);
    }
    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
} /* Output: (80% match)
(hi, 47)
(hi, 47)
(Amphibian@7d772e, hi, 47)
(Vehicle@757aef, Amphibian@d9f9c3, hi, 47)
(Vehicle@1a46e30, Amphibian@3e25a5, hi, 47, 11.1)
*///:~
