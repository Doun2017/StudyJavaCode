package com.example.doun.chapter16array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //practice 16.1
//        BerylliumSphere[] d = {new BerylliumSphere(),
//                new BerylliumSphere(), new BerylliumSphere()
//        };
//        showBerylliumSphere(d);
//        //不能使用聚集初始化
//        //showBerylliumSphere({new BerylliumSphere(),
//      //          new BerylliumSphere(), new BerylliumSphere()
//      //  });
//      //结论：You must use aggregate initialization at the point of an array variable’s
//      //definition. With dynamic initialization you can create and initialize an array
//      //object anywhere

        //practice 16.2
        showBerylliumSphere(createBerylliumSphere(12));


    }

    public void showBerylliumSphere(BerylliumSphere[] berylliumSpheres){
        for (BerylliumSphere b:berylliumSpheres){
            Log.d(TAG, b.toString());
        }
    }
    public BerylliumSphere[] createBerylliumSphere(int size){
        BerylliumSphere berylliumSphere[] = new BerylliumSphere[size];
        for (int i=0;i<size;i++){
            berylliumSphere[i] = new BerylliumSphere();
        }
        return berylliumSphere;
    }
}
