package com.doun.chapter8polymorphic;

import android.util.Log;

/**
 * Created by power on 2017/3/14,014.
 */

class CreateCount{
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

public class Rodent {
    private Food foodRodent;
    private CreateCount createCount;

    public Rodent(CreateCount createCount) {
        Log.i("Rodent", "Rodent construct");
        foodRodent = new Food("fruit");
        this.createCount = createCount;
        createCount.addRef();
    }

    public void dispose() {
        Log.i("Rodent", "disposing " + this);
        createCount.dispose();
    }


    String makeNoise() {
        return "Rodent Noise";
    }
}

class Mouse extends Rodent {
    private Food foodMouse;
    public Mouse(CreateCount createCount) {
        super(createCount);
        foodMouse = new Food("vegetables");
        Log.i("Mouse", "Mouse construct");
    }

    @Override
    String makeNoise() {
        return "Mouse Noise zhizhi...";
    }
}

class Gerbil extends Rodent {
    public Gerbil(CreateCount createCount) {
        super(createCount);
        Log.i("Gerbil", "Gerbil construct");

    }

    @Override
    String makeNoise() {
        return "Gerbil Noise jiji...";
    }
}

class Hamster extends Rodent {
    public Hamster(CreateCount createCount) {
        super(createCount);
        Log.i("Hamster", "Hamster construct");

    }

    @Override
    String makeNoise() {
        return "Hamster Noise chichi...";
    }
}