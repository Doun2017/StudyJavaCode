package com.doun.chapter9interface;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Doun on 2017/3/22.
 */

public class RandomChars {
    private int sum=0;
    private Random rand = new Random(47);

    public RandomChars(int sum) {
        this.sum = sum;
    }

    public char[] randomChars(){
        if (sum == 0)
            return null;
        char charArray[] = new char[sum];
        for(int i = 0; i < sum; i ++)
            charArray[i] = next();
        return charArray;
    }

    private char next() { return (char)(rand.nextInt(26)+97); }

}


class AdaptedRandomChars extends RandomChars
        implements Readable {
    private int readTimes=1;//需要read的次数

    @Override
    public int read(@NonNull CharBuffer cb) throws IOException {
        if(readTimes-- == 0)
            return -1;
        String result = new String(randomChars());
        result+="\n";
        cb.append(result);
        return result.length();
    }

    public AdaptedRandomChars(int count) {
        super(count);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new AdaptedRandomChars(7));
        while(s.hasNextLine())
            System.out.print(s.nextLine());
    }
} 