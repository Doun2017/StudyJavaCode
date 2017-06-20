package com.wsn.chapter12exception;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //practice12.1
//        try{
//            throw new Exception("message");
//
//        } catch (Exception e){
//            Log.d(TAG, e.getMessage());
//
//        }finally {
//            Log.d(TAG, "finally executed");
//        }

        //practice12.2
//        String str=null;
//        try{
//            Log.d(TAG, str);
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        //practice12.3
        //ArrayList<String> array = new ArrayList<>();
        String array[] = new String[2];
        try{
            String str = array[2];
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        //practice12.4


    }
}
