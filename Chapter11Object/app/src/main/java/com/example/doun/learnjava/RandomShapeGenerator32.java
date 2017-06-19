package com.example.doun.learnjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Doun on 2017/2/17.
 */

public class RandomShapeGenerator32 implements Iterable<Gerbil> {
    Gerbil gerbils[];

    RandomShapeGenerator32(int sum) {
        gerbils = new Gerbil[sum];
        for (int i=0; i<sum; i++)
        {
            gerbils[i] = new Gerbil(i);
        }
    }

    @Override
    public Iterator<Gerbil> iterator() {
        return new Iterator<Gerbil>() {
            private int num = 0;
            @Override
            public boolean hasNext() {
                return num<gerbils.length;
            }

            @Override
            public Gerbil next() {
                return gerbils[num++];
            }
        };
    }

    public Iterable<Gerbil> reversed() {
        return new Iterable<Gerbil>() {
            public Iterator<Gerbil> iterator() {
                return new Iterator<Gerbil>() {
                    int current = gerbils.length - 1;
                    public boolean hasNext() { return current > -1; }
                    public Gerbil next() { return gerbils[current--]; }
                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }


    public Iterable<Gerbil> randomized() {
        return new Iterable<Gerbil>() {
            public Iterator<Gerbil> iterator() {
                List<Gerbil> shuffled =
                        new ArrayList<Gerbil>(Arrays.asList(gerbils));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }
    
    
}