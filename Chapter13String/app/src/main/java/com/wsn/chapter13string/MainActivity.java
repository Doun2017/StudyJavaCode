package com.wsn.chapter13string;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //practice13.1
//        public String toString () {
//            return "valve1 = " + valve1 + " " +
//                    "valve2 = " + valve2 + " " +
//                    "valve3 = " + valve3 + " " +
//                    "valve4 = " + valve4 + "\n" +
//                    "i = " + i + " " + "f = " + f + " " +
//                    "source = " + source;
//        }
        //toString中没有使用循环，所以编译器默认只生成一个StringBuild对象。


        //practice13.2
//        Log.i(TAG, new InfiniteRecursion().toString());

        //practice13.3 见Turtle.java

        //practice13.4 Receipt.java

        //practice13.5 略

        //practice13.6
//        MyFormatClass myFormatClass = new MyFormatClass();
//        Log.d(TAG, myFormatClass.toString());

        //practice13.7
//        String strsToMatch[] = new String[]{"fdjasi", "Afdsfff", "Efjdis."};
//        for (String str:strsToMatch){
//            Log.d(TAG, "match result::"+matchMyPattern(str));
//        }

        //practice13.8
        String knights = "Then, when you have found the shrubbery, you must " +
                        "cut down the mightiest tree in the forest... " +
                        "with... a herring!";
        String strsToMatch[] = knights.split("the|you");
        for (String str:strsToMatch){
            Log.d(TAG, str);
        }



    }

    boolean matchMyPattern(String str){
        return str.matches("[A-Z].*\\.");
    }
}

class MyFormatClass{
    private int anInt=2;
    private long aLong=22;
    private float aFloat=22.33f;
    private double aDouble = 3333.133d;

    @Override
    public String toString() {
//        return "MyFormatClass{" +
//                "anInt=" + anInt +
//                ", aLong=" + aLong +
//                ", aFloat=" + aFloat +
//                ", aDouble=" + aDouble +
//                '}';
        return String.format("MyFormatClass{" +
                "anInt=%9d" +
                ", aLong=%9d" +
                ", aFloat=%9.5f" +
                ", aDouble=%9.5f" +'}', anInt, aLong, aFloat, aDouble);
    }
}