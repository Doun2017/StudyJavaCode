//: arrays/ContainerComparison.java
package com.example.doun.chapter16array;

import java.util.*;

import static com.example.doun.chapter16array.ArrayOptions.print;
//import static net.mindview.util.Print.*;
//
//class BerylliumSphere {
//    private static long counter;
//    private final long id = counter++;
//
//    public String toString() {
//        return "Sphere " + id;
//    }
//}

public class ContainerComparison {
    public static class
    BerylliumSphereGenerator implements Generator<com.example.doun.chapter16array.BerylliumSphere> {
        public com.example.doun.chapter16array.BerylliumSphere next() {
            return new com.example.doun.chapter16array.BerylliumSphere();
        }
    }

    public static void main(String[] args) {
        BerylliumSphereGenerator g = new BerylliumSphereGenerator();
        BerylliumSphere[] spheres = new BerylliumSphere[10];
        for (int i = 0; i < 5; i++)
            spheres[i] = g.next();
        print(Arrays.toString(spheres));
        print(spheres[4].toString());

        List<BerylliumSphere> sphereList =
                new ArrayList<BerylliumSphere>();
        for (int i = 0; i < 5; i++)
            sphereList.add(g.next());
        print(sphereList.toString());
        print(sphereList.get(4).toString());

        int[] integers = {0, 1, 2, 3, 4, 5};
        print(Arrays.toString(integers));
        print(integers[4] + "");

        List<Integer> intList = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3, 4, 5));
        intList.add(97);
        print(intList.toString());
        print(intList.get(4).toString());


        BerylliumSphere[] spheres1 = Generated.array(BerylliumSphere.class, new BerylliumSphereGenerator(), 10);
        print(Arrays.toString(spheres1));


    }
} /* Output:
[Sphere 0, Sphere 1, Sphere 2, Sphere 3, Sphere 4, null, null, null, null, null]
Sphere 4
[Sphere 5, Sphere 6, Sphere 7, Sphere 8, Sphere 9]
Sphere 9
[0, 1, 2, 3, 4, 5]
4
[0, 1, 2, 3, 4, 5, 97]
4
*///:~
