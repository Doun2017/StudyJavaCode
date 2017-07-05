package com.example.doun.chapter14rtti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //practice14.1 见toys包
        //用Class.newInstance()来创建的类，必须带有默认的构造器

        //practice14.2 见toys包
        //可正常显示新加的Interface

    }
}
