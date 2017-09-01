package com.example.doun.chapter17containers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedHashMap;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //practice 17.1-3 Practises.java
        //practice 17.4 CollectionDataTest.java
        //practice 17.5 CountingMapData.java和CountingMapData5.java习题答案

        //practice 17.6 Practises.java
        //practice 17.7 Practises.java
        //practice 17.8 E08_SList.java
        //practice 17.9 Practises.java
        //practice 17.10 E10_CustomSortedSet 习题答案
        //practice 17.11 Practice11.java
        //practice 17.12 AssociativeArray.java
        //practice 17.13 Practise13.java
        //practice 17.14 Maps.java
        //practice 17.15 Practise15.java
        //practice 17.16 SlowMap.java 主要解决entrySet()中产生的set返回的是副本而不是原始数据视图的问题。
        //practice 17.17 SlowMap.java 其所继承的AbstractMap<K, V>中已将大部分方法实现。
        //practice 17.18 SlowSet.java
        //practice 17.19 Practise19.java
        //practice 17.20 SimpleHashMap20.java
        //practice 17.21 SimpleHashMap21.java
        //practice 17.22 23 SimpleHashMap.java
        //practice 17.24 SimpleHashSet.java 自己没能实现，借鉴了官方答案
        //practice 17.25 SimpleHashMap25.java
        //practice 17.26 CountedString.java
        //practice 17.27 CountedString27.java 在hashcode中解除与id的绑定之后，找特定对象的操作会延伸至equals()，这样我们会损失一些散列的性能优势。
        //practice 17.28 Tuple.java 将原来TwoTuple ThreeTuple FourTuple FiveTuple之间的继承结构改为组合，因为这些泛型类都要实现
        // Comparable接口，若用继承无法同时正确实现compareTo()。对于equals()也是一样。借鉴了官方答案
        //practice 17.29 ListPerformance29.java
        //practice 17.30 ListPerformance30.java
        //practice 17.31 MyStringVector.java
        //practice 17.32 MyIntVector.java
        //practice 17.33 E33_ListPerformance3.java借鉴了官方答案
        //practice 17.34 SetPerformance34.java
        //practice 17.35 MapPerformance.java
    }
}
