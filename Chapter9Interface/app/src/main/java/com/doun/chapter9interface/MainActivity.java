package com.doun.chapter9interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Scanner;

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
//        ThreeFunctions threeFunctions = new ThreeFunctions();
//        threeFunctions.a();
//        threeFunctions.b();
//        threeFunctions.c();
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
//        Playable[] orchestra = {
//                new Wind(),
//                new Percussion(),
//                new Stringed(),
//                new Brass(),
//                new Woodwind()
//        };
//        tuneAll(orchestra);

        //practice 9.11
//        String w = new String("abcdefg");
//        Apply.process(new LettersExchangerAdapter(new LettersExchanger()), w);
//        Apply.process(new LettersExchangerAdapter(new LettersExchanger()), w);

        //practice 9.12
//        Hero h = new Hero();
//        Adventure.t(h); // Treat it as a CanFight
//        Adventure.u(h); // Treat it as a CanSwim
//        Adventure.v(h); // Treat it as a CanFly
//        Adventure.w(h); // Treat it as an ActionCharacter
//        Adventure.z(h);

        //practice 9.13
//        ImplementClass implementClass = new ImplementClass();
//        FirstInterface firstInterface = implementClass;
//        firstInterface.first();
//        SecondInterface secondInterface = implementClass;
//        secondInterface.first();
//        secondInterface.second();
//        ThirdInterface thirdInterface = implementClass;
//        thirdInterface.first();
//        thirdInterface.third();

        //practice 9.14 15
//        SonClass sonClass = new SonClass();
//        SonClass.A(sonClass);
//        SonClass.B(sonClass);
//        SonClass.C(sonClass);
//        SonClass.D(sonClass);


        //practice 9.16
//        Scanner s = new Scanner(new AdaptedRandomChars(7));
//        while(s.hasNext())
//        {
//            msgToShow += s.nextLine();
//            msgToShow += "\n";
//        }


        //practice 9.17
//        InterfaceOne interfaceOne = new SonClass();
//        //interfaceOne.a=2; //final
//        int i=InterfaceOne.a;//static

        //practice 9.18
//        UnicycleFactory unicycleFactory = new UnicycleFactory();
//        Cycle cycle1 = unicycleFactory.getCycle();
//        cycle1.f();
//        BicycleFactory bicycleFactory = new BicycleFactory();
//        Cycle cycle2 = bicycleFactory.getCycle();
//        cycle2.f();
//        TricycleFactory tricycleFactory = new TricycleFactory();
//        Cycle cycle3 = tricycleFactory.getCycle();
//        cycle3.f();

        //practice 9.19
        MyGameFactory coinGameFactory = new CoinGameFactory();
        MyGameFactory diceGameFactory = new DiceGameFactory();
        MyGame myGame1 = coinGameFactory.getGame();
        MyGame myGame2 = diceGameFactory.getGame();

        msgToShow += myGame1.play();
        msgToShow += "\n";

        msgToShow += myGame2.play();
        msgToShow += "\n";



        msgTextView.setText(msgToShow);
    }
}
