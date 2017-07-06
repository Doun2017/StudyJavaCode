package com.example.doun.chapter14rtti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

        //practice14.3
//        Rhomboid rhomboid = new Rhomboid();
//        Log.d(TAG, rhomboid.toString());
//        Shape shape = rhomboid;
//        Log.d(TAG, shape.toString());
//        Rhomboid rhomboid1 = (Rhomboid) shape;
//        Log.d(TAG, rhomboid1.toString());
//        //Triangle triangle = (Triangle)shape;// java.lang.ClassCastException:
//                                            // com.example.doun.chapter14rtti.Rhomboid cannot be cast to com.example.doun.chapter14rtti.Triangle
//        //Log.d(TAG, triangle.toString());

        //practice14.4
        Rhomboid rhomboid = new Rhomboid();
        Log.d(TAG, rhomboid.toString());
        Shape shape = rhomboid;
        Log.d(TAG, shape.toString());
        if (shape instanceof Rhomboid){
            Rhomboid rhomboid1 = (Rhomboid) shape;
            Log.d(TAG, rhomboid1.toString());
        }
        if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            Log.d(TAG, triangle.toString());
        }









    }
}
