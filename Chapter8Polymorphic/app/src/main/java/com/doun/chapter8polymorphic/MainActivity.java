package com.doun.chapter8polymorphic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView msgTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgTextView = (TextView) findViewById(R.id.msgTextView);
        String msgToShow = "";

        //practice 8.1
        Unicycle unicycle = new Unicycle();
        msgToShow += ride(unicycle);
        msgToShow += "\n";
        Bicycle bicycle = new Bicycle();
        msgToShow += ride(bicycle);
        msgToShow += "\n";
        Tricycle tricycle = new Tricycle();
        msgToShow += ride(tricycle);
        msgToShow += "\n";


        //practice 8.5
//        Unicycle unicycle = new Unicycle();
//        msgToShow += wheels(unicycle);
//        msgToShow += "\n";
//        Bicycle bicycle = new Bicycle();
//        msgToShow += wheels(bicycle);
//        msgToShow += "\n";
//        Tricycle tricycle = new Tricycle();
//        msgToShow += wheels(tricycle);
//        msgToShow += "\n";


//        //practice 8.6 8.7
//        Instrument[] orchestra = {
//                new Wind(),
//                new Percussion(),
//                new Stringed(),
//                new Brass(),
//                new Woodwind(),
//                new NewInstrument()
//        };
//        msgToShow += Music3.tuneAll(orchestra);
//        msgToShow += "\n";
//        msgToShow += "\n";
//        Instrument ins= new Instrument();
//        msgToShow += ins;



    //practice 8.8

//    RandomInstrumentGenerator gen = new RandomInstrumentGenerator();
//    Instrument[] orchestra = new Instrument[9];
//    // Fill up the array with shapes:
//    for(int i = 0; i < orchestra.length; i++)
//        orchestra[i] = gen.next();
//    // Make polymorphic method calls:
//    msgToShow += Music3.tuneAll(orchestra);

        //practice 8.10
//        Practice10 practice10ImportClass = new Practice10ImportClass();
//        msgToShow += practice10ImportClass.funOne();







        msgTextView.setText(msgToShow);
    }


    public String ride(Cycle cycle){
        return cycle.beRide();
    }
    public String wheels(Cycle cycle){
        return cycle.wheels();
    }
}
