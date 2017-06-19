package com.example.doun.learnjava;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Doun on 2017/2/16.
 */

public class RandomShapeGenerator31 implements Iterable<Gerbil> {
    private Random rand = new Random(47);
    Gerbil gerbils[];

    RandomShapeGenerator31(int sum) {
        gerbils = new Gerbil[sum];
        for (int i=0; i<sum; i++)
        {
            gerbils[i] = new Gerbil(rand.nextInt(100));
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
}
