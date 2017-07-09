package com.example.doun.chapter15generics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doun.chapter15generics.pets.Dog;
import com.example.doun.chapter15generics.pets.Pet;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //practice15.1
        Holder3<Pet> hTest = new Holder3<Pet>(new Dog());
        System.out.println(hTest.get());

        //practice15.2
        Holder3Objs<Pet> h3 = new Holder3Objs<Pet>(new Pet(),new Pet(),new Pet());
        System.out.println(h3.getA());
        System.out.println(h3.getB());
        System.out.println(h3.getC());



    }
}
