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
//        Unicycle unicycle = new Unicycle();
//        msgToShow += ride(unicycle);
//        msgToShow += "\n";
//        Bicycle bicycle = new Bicycle();
//        msgToShow += ride(bicycle);
//        msgToShow += "\n";
//        Tricycle tricycle = new Tricycle();
//        msgToShow += ride(tricycle);
//        msgToShow += "\n";


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


//        //practice 8.9 8.12
//        Rodent rodents[] = new Rodent[3];
//        rodents[0] = new Mouse();
//        rodents[1] = new Gerbil();
//        rodents[2] = new Hamster();


        //practice 8.10
//        Practice10 practice10ImportClass = new Practice10ImportClass();
//        msgToShow += practice10ImportClass.funOne();

        //practice 8.11
//        new Sandwich11();


        //practice 8.13
//        Shared shared = new Shared();
//        Composing[] composing = {new Composing(shared),
//                new Composing(shared), new Composing(shared),
//                new Composing(shared), new Composing(shared)};
//        //Composing scomposing = new Composing(shared);//有此句则打印 “ finalizing 此时refCount=1”
//        for (Composing c : composing)
//            c.dispose();

        //practice 8.14
//        Rodent rodents[] = new Rodent[3];
//        CreateCount createCount = new CreateCount();
//        rodents[0] = new Mouse(createCount);
//        rodents[1] = new Gerbil(createCount);
//        rodents[2] = new Hamster(createCount);
//        //new Mouse(createCount);
//        for (Rodent c : rodents)
//            c.dispose();


        //practice 8.15
//        new RectangularGlyph(12, 4);

        //practice 8.16
//        StarShip starShip = new StarShip();
//        for (int i=0; i<10; i++){
//            starShip.change();
//            starShip.performPlay();
//        }

        Cycle cycles[] = new Cycle[]{
            new Unicycle(),
            new Bicycle(), new Tricycle()
        };
        for (Cycle c:cycles)
        {
            msgToShow += c.balance();
            msgToShow += "\n";
        }
        Unicycle unicycle = (Unicycle)cycles[0];
        msgToShow += unicycle.balance();
        msgToShow += "\n";
        Bicycle bicycle = (Bicycle)cycles[1];
        msgToShow += bicycle.balance();
        msgToShow += "\n";
        Tricycle tricycle = (Tricycle)cycles[2];
        msgToShow += tricycle.balance();
        msgToShow += "\n";

        msgTextView.setText(msgToShow);
    }


    public String ride(Cycle cycle){
        return cycle.beRide();
    }
    public String wheels(Cycle cycle){
        return cycle.wheels();
    }
}
