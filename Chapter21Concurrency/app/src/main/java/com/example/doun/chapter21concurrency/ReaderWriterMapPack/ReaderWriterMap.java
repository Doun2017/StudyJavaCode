//: concurrency/ReaderWriterMap.java
package com.example.doun.chapter21concurrency.ReaderWriterMapPack;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

//import static net.mindview.util.Print.*;

public class ReaderWriterMap<K, V> extends AbstractMap<K, V>{
    @Override
    public Set<Entry<K, V>> entrySet() {
        return hashMap.entrySet();
    }

    private HashMap<K, V> hashMap;
    // Make the ordering fair:
    private ReentrantReadWriteLock lock =new ReentrantReadWriteLock(true);

    public ReaderWriterMap(HashMap<K, V> map) {
        hashMap = new HashMap<>(map);
    }

    public V put(K element, V value) {
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return hashMap.put(element, value);
        } finally {
            wlock.unlock();
        }
    }

    public V get(Object element) {
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            // Show that multiple readers
            // may acquire the read lock:
//            if (lock.getReadLockCount() > 1)
//                System.out.println(lock.getReadLockCount());
            return hashMap.get(element);
        } finally {
            rlock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        new ReaderWriterMapTest1(30, 1);
    }
}

class ReaderWriterMapTest1 {
    ExecutorService exec = Executors.newCachedThreadPool();
    private final static int SIZE = 100;
    private static Random rand = new Random(47);
    private ReaderWriterMap<Integer, Integer> list = new ReaderWriterMap<>(MapData.map(new CountingGenerator.Integer(), new CountingGenerator.Integer(), SIZE));

    private class Writer implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 20; i++) { // 2 second test
                    list.put(i, rand.nextInt());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                // Acceptable way to exit
            }
            System.out.println("Writer finished, shutting down");
            exec.shutdownNow();
        }
    }

    private class Reader implements Runnable {
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    for (int i = 0; i < SIZE; i++) {
                        list.get(i);
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                }
            } catch (InterruptedException e) {
                // Acceptable way to exit
            }
        }
    }

    public ReaderWriterMapTest1(int readers, int writers) {
        for (int i = 0; i < readers; i++)
            exec.execute(new Reader());
        for (int i = 0; i < writers; i++)
            exec.execute(new Writer());
    }
} /* (Execute to see output) *///:~
