package com.doun.chapter9interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.doun.chapter9interface.Music5.tuneAll;

public class MainActivity extends AppCompatActivity {
    TextView msgTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgTextView = (TextView) findViewById(R.id.msgTextView);
        String msgToShow = "";

        //practice 9.1 9.7
//        Rodent rodents[] = new Rodent[3];
//        CreateCount createCount = new CreateCount();
//        rodents[0] = new Mouse(createCount);
//        rodents[1] = new Gerbil(createCount);
//        rodents[2] = new Hamster(createCount);
//        //new Mouse(createCount);
//        for (Rodent c : rodents){
//            msgToShow += c.makeNoise();
//            msgToShow += "\n";
//        }


        //practice 9.2
//        TestAbstractClass test = new TestAbstractClass();

        //practice 9.3
//        ExtentPrint extentPrint = new ExtentPrint();
//        extentPrint.print();//说明了对象构造的顺序：基类的域；基类构造方法；导出类的域；导出类的构造方法。

        //practice 9.4
//        E04_AbstractBase.test1(new Extended1());
//        E04_AbstractBase.test2(new Extended2());

        //practice 9.5
        ThreeFunctions threeFunctions = new ThreeFunctions();
        threeFunctions.a();
        threeFunctions.b();
        threeFunctions.c();
        //practice 9.6 略

        //practice 9.8
//        new Sandwich();

        //practice 9.9
//        Instrument[] orchestra = {
//                new Wind(),
//                new Percussion(),
//                new Stringed(),
//                new Brass(),
//                new Woodwind()
//        };
//        tuneAll(orchestra);

        //practice 9.10
        Playable[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);



        msgTextView.setText(msgToShow);
    }
}
