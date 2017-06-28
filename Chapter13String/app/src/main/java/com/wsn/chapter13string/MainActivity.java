package com.wsn.chapter13string;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

        //practice13.4
        System.out.println(String.format("%2$08d", -3123,-5566));

    }
}
