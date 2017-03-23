package com.doun.chapter10innerclass;

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



        //practice10.1
//        Outer outer = new Outer();
//        Outer.Inner inner = outer.newInner();


        //practice10.2
        Sequence sequence = new Sequence(10);
        for (int i=0; i<10; i++){
            sequence.add(new Outer("outer"+i));
        }

        Selector selector = sequence.selector();
        while(!selector.end()) {
            msgToShow+=(selector.current() + "\n");
            selector.next();
        }

        //practice10.3
//        Outer outer = new Outer("I'm OuterClass");
//        Outer.Inner inner = outer.newInner();
//        msgToShow += inner.toString();




        msgTextView.setText(msgToShow);
    }
}
