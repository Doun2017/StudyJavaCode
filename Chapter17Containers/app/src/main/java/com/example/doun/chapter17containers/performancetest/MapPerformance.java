//: containers/MapPerformance.java
// Demonstrates performance differences in Maps.
// {Args: 100 5000} Small to keep build testing short
package com.example.doun.chapter17containers.performancetest;

import com.example.doun.chapter17containers.SlowMap;

import java.util.*;

public class MapPerformance {
    static List<Test<Map<Integer, Integer>>> tests =
            new ArrayList<Test<Map<Integer, Integer>>>();

    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++)
                        map.put(j, j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("get") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("iterate") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * map.size();
            }
        });
    }

    public static void main(String[] args) {
        Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 5000);

        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);


//        Tester.run(new TreeMap<Integer, Integer>(), tests);
//        Tester.run(new HashMap<Integer, Integer>(), tests);
//        Tester.run(new LinkedHashMap<Integer, Integer>(), tests);
//        Tester.run(new IdentityHashMap<Integer, Integer>(), tests);
//        Tester.run(new WeakHashMap<Integer, Integer>(), tests);
//        Tester.run(new Hashtable<Integer, Integer>(), tests);

//        Tester.run(new SlowMap<Integer, Integer>(), tests);

        Tester.run(new MapEntrySlowMap1<Integer, Integer>(), tests);
        Tester.run(new MapEntrySlowMap2<Integer, Integer>(), tests);
    }
} /* Output: (Sample)
---------- TreeMap ----------
 size     put     get iterate
   10     748     168     100
  100     506     264      76
 1000     771     450      78
10000    2962     561      83
---------- HashMap ----------
 size     put     get iterate
   10     281      76      93
  100     179      70      73
 1000     267     102      72
10000    1305     265      97
------- LinkedHashMap -------
 size     put     get iterate
   10     354     100      72
  100     273      89      50
 1000     385     222      56
10000    2787     341      56
------ IdentityHashMap ------
 size     put     get iterate
   10     290     144     101
  100     204     287     132
 1000     508     336      77
10000     767     266      56
-------- WeakHashMap --------
 size     put     get iterate
   10     484     146     151
  100     292     126     117
 1000     411     136     152
10000    2165     138     555
--------- Hashtable ---------
 size     put     get iterate
   10     264     113     113
  100     181     105      76
 1000     260     201      80
10000    1245     134      77
*///:~
/*
---------- TreeMap ----------
 size     put     get iterate
   10     449     272     100
  100     116     127       7
 1000     100      66      10
10000     101     102      33
---------- HashMap ----------
 size     put     get iterate
   10     206     114      73
  100      33      10      11
 1000      31      11       9
10000      29      29      31
------- LinkedHashMap -------
 size     put     get iterate
   10     123      51      18
  100      37      16       8
 1000      42      19       8
10000      44      22      17
------ IdentityHashMap ------
 size     put     get iterate
   10     115      39      34
  100      30      38      29
 1000      79      75      28
10000      91      85      27
-------- WeakHashMap --------
 size     put     get iterate
   10     130      46      26
  100      43      12      14
 1000      36      15      14
10000      36      23     141
--------- Hashtable ---------
 size     put     get iterate
   10      86      38      26
  100      52      31      12
 1000      48      34      12
10000      51      37      18
---------- SlowMap ----------
 size     put     get iterate
   10     160     112      34
  100     255     188      20
 1000    2242    1669      22
10000   22756   17693      21
*/
/*
------ MapEntrySlowMap1 ------
 size     put     get iterate
   10     660     255      53
  100     225     292       2
 1000    1572    2268       1
------ MapEntrySlowMap2 ------
 size     put     get iterate
   10     466     275      24
  100     595      51       2
 1000    5913      76       1
 */