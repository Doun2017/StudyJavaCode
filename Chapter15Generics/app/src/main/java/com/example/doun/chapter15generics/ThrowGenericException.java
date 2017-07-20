//: generics/ThrowGenericException.java
package com.example.doun.chapter15generics;

import java.util.*;

interface Processor<T, E extends Exception, X extends Exception> {
    void process(List<T> resultCollector) throws E;
    void reprocess(List<T> resultCollector) throws X;
}

class ProcessRunner<T, E extends Exception, X extends Exception> extends ArrayList<Processor<T, E, X>> {
    List<T> processAll() throws E, X{
        List<T> resultCollector = new ArrayList<T>();
        for (Processor<T, E, X> processor : this)
            processor.process(resultCollector);
        return resultCollector;
    }
    List<T> reprocessAll() throws E, X{
        List<T> resultCollector = new ArrayList<T>();
        for (Processor<T, E, X> processor : this)
            processor.reprocess(resultCollector);
        return resultCollector;
    }
}

class Failure1 extends Exception {
}

class Failure2 extends Exception {
}

class Processor1 implements Processor<String, Failure1, Failure2> {
    static int MAX=6;
    static int count = 3;

    public void process(List<String> resultCollector) throws Failure1 {
        if (count-- > 1)
            resultCollector.add("Hep!"+count);
        else
            resultCollector.add("Ho!"+count);
        if (count < 0)
            throw new Failure1();
    }

    public void reprocess(List<String> resultCollector) throws Failure2 {
        if (count++ <  MAX-1)
            resultCollector.add("-Hep!"+count);
        else
            resultCollector.add("-Ho!"+count);
        if (count > MAX)
            throw new Failure2();
    }
}

//
//class Processor2 implements Processor<Integer, Failure2> {
//    static int count = 2;
//
//    public void
//    process(List<Integer> resultCollector) throws Failure2 {
//        if (count-- == 0)
//            resultCollector.add(47);
//        else {
//            resultCollector.add(11);
//        }
//        if (count < 0)
//            throw new Failure2();
//    }
//}

public class ThrowGenericException {
    public static void main(String[] args) {
        ProcessRunner<String, Failure1, Failure2> runner = new ProcessRunner<String, Failure1, Failure2>();
        for (int i = 0; i < 3; i++)
            runner.add(new Processor1());
        try {
            System.out.println(runner.processAll());
        } catch (Failure1 e) {
            System.out.println(e);
        } catch (Failure2 e) {
            System.out.println(e);
        }

        try {
            System.out.println(runner.reprocessAll());
        } catch (Failure1 e) {
            System.out.println(e);
        } catch (Failure2 e) {
            System.out.println(e);
        }

    }
} ///:~
