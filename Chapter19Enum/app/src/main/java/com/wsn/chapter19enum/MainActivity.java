package com.wsn.chapter19enum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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



    }
}
