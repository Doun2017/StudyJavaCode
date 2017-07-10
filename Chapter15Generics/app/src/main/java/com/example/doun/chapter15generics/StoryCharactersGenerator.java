//: generics/StoryCharacters/StoryCharactersGenerator.java
// Generate different types of StoryCharacters:
package com.example.doun.chapter15generics;

import java.util.*;

class StoryCharacters {
    private static long counter = 0;
    private final long id = counter++;

    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
} ///:~

class GoodGays extends StoryCharacters {}
class QingTianZhu extends GoodGays {}
class DaHuangFeng extends GoodGays {}
class BadGays extends StoryCharacters {}
class WeiZhenTian extends BadGays {}
class BaTianHu extends BadGays {}


public class StoryCharactersGenerator implements Generator<StoryCharacters>, Iterable<StoryCharacters> {
    private Class[] types = {GoodGays.class, QingTianZhu.class, DaHuangFeng.class,
            BadGays.class, WeiZhenTian.class, BaTianHu.class,};
    private static Random rand = new Random(47);

    public StoryCharactersGenerator() {
    }

    // For iteration:
    private int size = 0;

    public StoryCharactersGenerator(int sz) {
        size = sz;
    }

    public StoryCharacters next() {
        try {
            return (StoryCharacters)
                    types[rand.nextInt(types.length)].newInstance();
            // Report programmer errors at run time:
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class StoryCharactersIterator implements Iterator<StoryCharacters> {
        int count = size;

        public boolean hasNext() {
            return count > 0;
        }

        public StoryCharacters next() {
            count--;
            return StoryCharactersGenerator.this.next();
        }

        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<StoryCharacters> iterator() {
        return new StoryCharactersIterator();
    }

    public static void main(String[] args) {
        StoryCharactersGenerator gen = new StoryCharactersGenerator();
        for (int i = 0; i < 5; i++)
            System.out.println(gen.next());
        for (StoryCharacters c : new StoryCharactersGenerator(5))
            System.out.println(c);
    }
} /* Output:
Americano 0
Latte 1
Americano 2
Mocha 3
Mocha 4
Breve 5
Americano 6
Latte 7
Cappuccino 8
Cappuccino 9
*///:~
