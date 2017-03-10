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
        Cycle unicycle = new Unicycle();
        msgToShow += unicycle.ride();
        msgToShow += "\n";
        Cycle bicycle = new Bicycle();
        msgToShow += bicycle.ride();
        msgToShow += "\n";
        Cycle tricycle = new Tricycle();
        msgToShow += tricycle.ride();
        msgToShow += "\n";

        //practice 8.2

        msgTextView.setText(msgToShow);
    }
}
