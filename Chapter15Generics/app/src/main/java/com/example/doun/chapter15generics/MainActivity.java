package com.example.doun.chapter15generics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.doun.chapter15generics.pets.Dog;
import com.example.doun.chapter15generics.pets.Pet;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //practice15.1
//        Holder3<Pet> hTest = new Holder3<Pet>(new Dog());
//        System.out.println(hTest.get());

        //practice15.2
//        Holder3Objs<Pet> h3 = new Holder3Objs<Pet>(new Pet(),new Pet(),new Pet());
//        System.out.println(h3.getA());
//        System.out.println(h3.getB());
//        System.out.println(h3.getC());

        //practice15.3
//        System.out.println(k());

        //practice15.4
        Sequence<String> sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Selector<String> selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            Log.d(TAG, selector.current() + " ");
            selector.next();
        }

        //practice15.5 LinkedStack.java







    }


    static SixTuple<String,String,String,Integer,Double,Float> k() {
        return new SixTuple<String,String,String,Integer,Double,Float>(
                "one","two", "hi", 47, 11.1, 2.656f);
    }


}
