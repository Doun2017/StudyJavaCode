package com.wsn.chapter13string;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
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
//        String knights = "Then, when you have found the shrubbery, you must " +
//                "cut down the mightiest tree in the forest... " +
//                "with... a herring!";
//        String strsToMatch[] = knights.split("the|you");
//        for (String str : strsToMatch) {
//            Log.d(TAG, str);
//        }

        //practice13.9
//        Log.e(TAG, knights);
//        Log.e(TAG, knights.replaceAll("[aeiou]", "_"));

        //practice13.10
//        String staStr = "Java now has regular expressions";
//        String regularStr[] = new String[]{"^Java", "\\Breg.*", "n.w\\s+h(a|i)s",
//                "s?", "s*", "s+",
//                "s{4}", "s{1}", "s{0,3}"};
//        for (String str : regularStr) {
//            //Log.d(TAG, str);
//            //Log.d(TAG, "match result::"+staStr.matches(str));
//
//            Log.d(TAG, "Regular expression: \"" + str + "\"");
//            Pattern p = Pattern.compile(str);
//            Matcher m = p.matcher(staStr);
//            while(m.find()) {
//                Log.d(TAG, "Match \"" + m.group() +
//                        "\" at positions " + m.start() + "-" +
//                        (m.end() - 1));
//            }
//        }

        //practice13.11
//        Pattern p = Pattern.compile("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b");
//        Matcher m = p.matcher("Arline ate eight apples and one orange while Anita hadn't any");
//        while(m.find()) {
//            Log.d(TAG, "Match \"" + m.group() +
//                    "\" at positions " + m.start() + "-" +
//                    (m.end() - 1));
//        }

        //practice13.12
        final String POEM = "Twas brillig, and the slithy toves\n" +
                        "Did gyre and gimble in the wabe.\n" +
                        "All mimsy were the borogoves,\n" +
                        "And the mome raths outgrabe.\n\n" +
                        "Beware the Jabberwock, my son,\n" +
                        "The jaws that bite, the claws that catch.\n" +
                        "Beware the Jubjub bird, and shun\n" +
                        "The frumious Bandersnatch.";
//        Matcher m = Pattern.compile("(\\b[a-z][a-zA-Z]+\\b)").matcher(POEM);//我的答案
        Matcher m = Pattern.compile("\\b((?![A-Z])\\w+)\\b").matcher(POEM);//标准答案  (?![A-Z])非获取匹配，正向否定预查，在任何不匹配pattern的字符串开始处匹配查找字符串，该匹配不需要获取供以后使用
        Set<String> stringSet = new HashSet<>();
        while(m.find()) {
            String groupStr = "";
            for(int j = 0; j <= m.groupCount(); j++) {
                groupStr += "[" + m.group(j) + "]";
                stringSet.add(m.group(j));
            }
            Log.d("match", groupStr);
        }
        Log.d("match", "sum = " + stringSet.size() + stringSet.toString());

    }

    boolean matchMyPattern(String str) {
        return str.matches("[A-Z].*\\.");
    }
}

class MyFormatClass {
    private int anInt = 2;
    private long aLong = 22;
    private float aFloat = 22.33f;
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
                ", aDouble=%9.5f" + '}', anInt, aLong, aFloat, aDouble);
    }
}