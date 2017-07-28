package com.example.doun.chapter16array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.Random;

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
//        showBerylliumSphere(createBerylliumSphere(12));

        //practice 16.3
//        printDoubles(createDoubles(5,4,1.9,48.9));

        //practice 16.4
        printDoubles3(createDoubles3(5,4,2,1.9,48.9));
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

    public void printDoubles(double[][] doubles){
        StringBuilder builder=new StringBuilder();

        for (double[] doubles1:doubles){
            builder.append(Arrays.toString(doubles1));
            builder.append("\n");
        }

        Log.d(TAG, builder.toString());
    }

    public double[][] createDoubles(int lines, int lows, double min, double max){
        if (!(min < max))
            throw new IllegalArgumentException("BadRange");

        double [][]doubles = new double[lines][lows];
        for (int i=0;i<lines;i++){
            for (int j=0;j<lows;j++){
                double d = min+ Math.random()*(max-min);
                doubles[i][j]=d;
            }
        }
        return doubles;
    }


    public void printDoubles3(double[][][] doubles){
        Log.d(TAG, Arrays.deepToString(doubles));

//        for (double[][] doubles2:doubles){
//            for (double[] doubles1:doubles2){
//                Log.d(TAG, Arrays.toString(doubles1));
//            }
//        }
    }

    public double[][][] createDoubles3(int lines, int lows,int z, double min, double max){
        if (!(min < max))
            throw new IllegalArgumentException("BadRange");

        double [][][]doubles = new double[lines][lows][z];
        for (int i=0;i<lines;i++){
            for (int j=0;j<lows;j++){
                for (int k=0;k<z;k++){
                    double d = min+ Math.random()*(max-min);
                    doubles[i][j][k]=d;
                }
            }
        }
        return doubles;
    }
}
