package com.example.doun.chapter16array;

/**
 * Created by power on 2017/8/8,008.
 */

public class Practice19 {
    public int getMyi() {
        return myi;
    }

    private int myi;

    public Practice19(int myi) {
        this.myi = myi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Practice19 that = (Practice19) o;

        return myi == that.myi;

    }

    @Override
    public int hashCode() {
        return myi;
    }
}
