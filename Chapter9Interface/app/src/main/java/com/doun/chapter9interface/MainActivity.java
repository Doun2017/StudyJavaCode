package com.doun.chapter9interface;

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

        //practice 9.1
        Rodent rodents[] = new Rodent[3];
        CreateCount createCount = new CreateCount();
        rodents[0] = new Mouse(createCount);
        rodents[1] = new Gerbil(createCount);
        rodents[2] = new Hamster(createCount);
        //new Mouse(createCount);
        for (Rodent c : rodents){
            msgToShow += c.makeNoise();
            msgToShow += "\n";
        }















        msgTextView.setText(msgToShow);
    }
}
