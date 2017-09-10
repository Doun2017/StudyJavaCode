//: concurrency/MapComparisons.java
// {Args: 1 10 10} (Fast verification check during build)
// Rough comparison of thread-safe Map performance.
package com.example.doun.chapter21concurrency.ReaderWriterMapPack;

import java.util.concurrent.*;
import java.util.*;
//import net.mindview.util.*;

abstract class MapTest extends Tester<Map<Integer, Integer>> {
    MapTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    class Reader extends TestTask {
        long result = 0;

        void test() {
            for (long i = 0; i < testCycles; i++)
                for (int index = 0; index < containerSize; index++)
                    result += testContainer.get(index);
        }

        void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    class Writer extends TestTask {
        void test() {
            for (long i = 0; i < testCycles; i++)
                for (int index = 0; index < containerSize; index++)
                    testContainer.put(index, writeData[index]);
        }

        void putResults() {
            writeTime += duration;
        }
    }

    void startReadersAndWriters() {
        for (int i = 0; i < nReaders; i++)
            exec.execute(new Reader());
        for (int i = 0; i < nWriters; i++)
            exec.execute(new Writer());
    }
}

class SynchronizedHashMapTest extends MapTest {
    Map<Integer, Integer> containerInitializer() {
        return Collections.synchronizedMap(new HashMap<>(
                MapData.map(new CountingGenerator.Integer(), new CountingGenerator.Integer(), containerSize)));
    }

    SynchronizedHashMapTest(int nReaders, int nWriters) {
        super("Synched HashMap", nReaders, nWriters);
    }
}

class ReaderWriterMapTest extends MapTest {
    Map<Integer, Integer> containerInitializer() {
        return new ReaderWriterMap<>(
                MapData.map(new CountingGenerator.Integer(),new CountingGenerator.Integer(), containerSize));
    }

    ReaderWriterMapTest(int nReaders, int nWriters) {
        super("ReaderWriterMap", nReaders, nWriters);
    }
}
class ConcurrentHashMapTest extends MapTest {
    Map<Integer, Integer> containerInitializer() {
        return new ConcurrentHashMap<>(
                MapData.map(new CountingGenerator.Integer(),new CountingGenerator.Integer(), containerSize));
    }

    ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }
}

public class MapComparisons {
    public static void main(String[] args) {
        Tester.initMain(args);
//        new SynchronizedHashMapTest(10, 0);
//        new SynchronizedHashMapTest(9, 1);
//        new SynchronizedHashMapTest(5, 5);
//        new ConcurrentHashMapTest(10, 0);
//        new ConcurrentHashMapTest(9, 1);
//        new ConcurrentHashMapTest(5, 5);
        new ReaderWriterMapTest(4, 0);
        new ReaderWriterMapTest(3, 1);
        new ReaderWriterMapTest(2, 2);
        Tester.exec.shutdown();
    }
} /* Output: (Sample)
Type                             Read time     Write time
Synched HashMap 10r 0w        306052025049              0
Synched HashMap 9r 1w         428319156207    47697347568
readTime + writeTime =        476016503775
Synched HashMap 5r 5w         243956877760   244012003202
readTime + writeTime =        487968880962
ConcurrentHashMap 10r 0w       23352654318              0
ConcurrentHashMap 9r 1w        18833089400     1541853224
readTime + writeTime =         20374942624
ConcurrentHashMap 5r 5w        12037625732    11850489099
readTime + writeTime =         23888114831
*///:~
/*
*
* Type                             Read time     Write time
ReaderWriterMap 4r 0w           8310113569              0
ReaderWriterMap 4r 0w           8621235931              0
ReaderWriterMap 4r 0w           8094007900              0
ReaderWriterMap 4r 0w           8006318115              0
ReaderWriterMap 4r 0w           7827871396              0
ReaderWriterMap 4r 0w           7468953918              0
ReaderWriterMap 4r 0w           8778437885              0
ReaderWriterMap 4r 0w           8551024508              0
ReaderWriterMap 4r 0w           7693524739              0
ReaderWriterMap 4r 0w           7299193884              0
ReaderWriterMap 3r 1w          55879872584    18651757911
readTime + writeTime =         74531630495
ReaderWriterMap 3r 1w          47554318284    15857131111
readTime + writeTime =         63411449395
ReaderWriterMap 3r 1w          47609738628    16021215205
readTime + writeTime =         63630953833
ReaderWriterMap 3r 1w          51049266341    17017036313
readTime + writeTime =         68066302654
ReaderWriterMap 3r 1w          51699151752    17202562243
readTime + writeTime =         68901713995
ReaderWriterMap 3r 1w          47192522913    15901202634
readTime + writeTime =         63093725547
ReaderWriterMap 3r 1w          47733556340    15923097048
readTime + writeTime =         63656653388
ReaderWriterMap 3r 1w          47937098832    15944508303
readTime + writeTime =         63881607135
ReaderWriterMap 3r 1w          48409039373    16132124188
readTime + writeTime =         64541163561
ReaderWriterMap 3r 1w          51721333617    15800600042
readTime + writeTime =         67521933659
ReaderWriterMap 2r 2w          32227921003    32227439881
readTime + writeTime =         64455360884

ReaderWriterMap 2r 2w          33402253727    33467519389
readTime + writeTime =         66869773116

ReaderWriterMap 2r 2w          32271347292    32233408028
readTime + writeTime =         64504755320
ReaderWriterMap 2r 2w          32909704820    32908381737
readTime + writeTime =         65818086557
ReaderWriterMap 2r 2w          31860137779    31854836950
readTime + writeTime =         63714974729
ReaderWriterMap 2r 2w          31426967934    31006960968
readTime + writeTime =         62433928902
ReaderWriterMap 2r 2w          31858043406    31863743130
readTime + writeTime =         63721786536
ReaderWriterMap 2r 2w          31624981949    31618413420
readTime + writeTime =         63243395369
ReaderWriterMap 2r 2w          31965040560    32000721682
readTime + writeTime =         63965762242
ReaderWriterMap 2r 2w          31968171926    31851234318
readTime + writeTime =         63819406244
*
* */