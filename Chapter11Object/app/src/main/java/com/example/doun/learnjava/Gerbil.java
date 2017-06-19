package com.example.doun.learnjava;

/**
 * Created by Doun on 2017/2/12.
 */

public class Gerbil {
    private int gerbilNumber;
    Gerbil(int i) {
        gerbilNumber = i;
    }

    String hop() {
        String msg = "Mouse number " + gerbilNumber;
        msg += " It's hopping!";
        return msg;
    }

    @Override
    public boolean equals(Object obj) {
        Gerbil g = (Gerbil)obj;
        if (this.gerbilNumber != g.gerbilNumber)
        {
            return false;
        }
        return super.equals(obj);
//        return true;
    }
}
