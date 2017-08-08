package com.example.doun.chapter16array;

import java.util.Arrays;

import static com.example.doun.chapter16array.ArrayOptions.print;

/**
 * Created by power on 2017/8/8,008.
 */
public class SkipGenerator {

    public static class Boolean implements Generator<java.lang.Boolean> {
        private boolean value = false;
        private int skipValue = 1;

        public Boolean(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Boolean next() {
            for (int i = 0; i < skipValue; i++) value = !value; /* Just flips back and forth*/
            return value;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;
        private int skipValue = 1;

        public Byte(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Byte next() {
            return value+=skipValue;
//            return value++;
        }
    }

    static char[] chars = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static class Character implements Generator<java.lang.Character> {
        int index = -1;
        private int skipValue = 1;

        public Character(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Character next() {
            index = (index + skipValue) % chars.length;
//            index = (index + 1) % chars.length;
            return chars[index];
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        Generator<java.lang.Character> cg = new Character(1);

        public String() {
        }

        public String(int length, int skip) {
            cg = new Character(skip);
            this.length = length;
        }

        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++) buf[i] = cg.next();
            return new java.lang.String(buf);
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        private short value = 0;
        private int skipValue = 1;

        public Short(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Short next() {
            return value+=skipValue;
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int value = 0;
        private int skipValue = 1;

        public Integer(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Integer next() {
            return value+=skipValue;
//            return value++;
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private long value = 0;
        private int skipValue = 1;

        public Long(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Long next() {
            return value+=skipValue;
//            return value++;
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float value = 0;
        private int skipValue = 1;

        public Float(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Float next() {
            float result = value;
            value += skipValue;
            return result;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        private double value = 0.0;
        private int skipValue = 1;

        public Double(int skip) {
            this.skipValue = skip;
        }

        public java.lang.Double next() {
            double result = value;
            value += skipValue;
            return result;
        }
    }

    public static void main(java.lang.String[] args) {
        int size = 6;
        boolean[] a1 = ConvertTo.primitive(Generated.array(java.lang.Boolean.class, new SkipGenerator.Boolean(1), size));
        print("a1 = " + Arrays.toString(a1));
        byte[] a2 = ConvertTo.primitive(Generated.array(java.lang.Byte.class, new SkipGenerator.Byte(2), size));
        print("a2 = " + Arrays.toString(a2));
        char[] a3 = ConvertTo.primitive(Generated.array(java.lang.Character.class, new Character(3), size));
        print("a3 = " + Arrays.toString(a3));
        short[] a4 = ConvertTo.primitive(Generated.array(java.lang.Short.class, new SkipGenerator.Short(4), size));
        print("a4 = " + Arrays.toString(a4));
        int[] a5 = ConvertTo.primitive(Generated.array(java.lang.Integer.class, new SkipGenerator.Integer(5), size));
        print("a5 = " + Arrays.toString(a5));
        long[] a6 = ConvertTo.primitive(Generated.array(java.lang.Long.class, new SkipGenerator.Long(6), size));
        print("a6 = " + Arrays.toString(a6));
        float[] a7 = ConvertTo.primitive(Generated.array(java.lang.Float.class, new SkipGenerator.Float(7), size));
        print("a7 = " + Arrays.toString(a7));
        double[] a8 = ConvertTo.primitive(Generated.array(java.lang.Double.class, new SkipGenerator.Double(8), size));
        print("a8 = " + Arrays.toString(a8));
    }
}