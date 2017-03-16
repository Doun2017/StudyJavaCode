package com.doun.chapter9interface;

import android.util.Log;

/**
 * Created by power on 2017/3/14,014.
 */

class CreateCount {
    private int createdCount = 0;
    private static long counter = 0;
    private final long id = counter++;


    public CreateCount() {
        Log.i("CreateCount", "Creating " + this);
    }

    @Override
    protected void finalize() throws Throwable {
        Log.i("CreateCount", "finalizing createdCount=" + createdCount);

        super.finalize();
    }

    public void addRef() {
        createdCount++;
    }

    protected void dispose() {
        if (--createdCount == 0)
            Log.i("CreateCount", "Disposing " + this);
    }

    public String toString() {
        return "CreateCount.id=" + id;
    }
}


class Food {
    public Food(String food) {
        this.food = food;
        Log.i("Food", this.food);
    }

    String food = "rice";

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}

public interface Rodent {
    String makeNoise();
}

class Mouse implements Rodent {


    private Food foodMouse;

    public Mouse(CreateCount createCount) {
        foodMouse = new Food("vegetables");
        Log.i("Mouse", "Mouse construct");
    }

    @Override
    public String makeNoise() {
        return "Mouse Noise zhizhi...";
    }
}

class Gerbil implements Rodent {
    public Gerbil(CreateCount createCount) {
        Log.i("Gerbil", "Gerbil construct");
    }

    @Override
    public String makeNoise() {
        return "Gerbil Noise jiji...";
    }
}

class Hamster implements Rodent {
    public Hamster(CreateCount createCount) {
        Log.i("Hamster", "Hamster construct");
    }

    @Override
    public String makeNoise() {
        return "Hamster Noise chichi...";
    }
}