package com.wsn.chapter19enum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.EnumSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //practice 19.1 TrafficLight.java
        //practice 19.2 EnumImplementation.java
        //practice 19.3 Course.java Food.java Meal.java
        //practice 19.4 Meal2.java
        //practice 19.5 VowelsAndConsonants.java
        //practice 19.6 嵌在一起的好，更易于维护
//        We nest Appetizer, MainCourse, Dessert, and Coffee inside Food rather
//        than making them independent enums that coincidentally use Food; this
//        produces a clearer structure. When you see Food.Appetizer in the code, you
//        know the terms are related. The clarity and comprehensibility of code are
//        crucial, especially during maintenance. Remember, when you reduce software
//        maintenance you increase profit.
//        Nesting has another benefit when you use reflection to build up the grouping
//        enum (like Course from TIJ4). Try this as an additional exercise with TIJ4’s
//        enumerated/menu/Meal.java program. Pay special attention to overcoming
//        the JDK’s constraints on the java.lang.Enum class.

        //practice 19.7 EnumSet是一个虚类，它有两个实现类JumboEnumSet和RegularEnumSet，前者
        //处理多与64个元素的情况，后者处理最多64各元素的情况。EnumSet的主入口是static方法noneOf(),
        //其他的static方法都会调用此方法。

        //practice 19.8 PostOffice.java



    }
}
