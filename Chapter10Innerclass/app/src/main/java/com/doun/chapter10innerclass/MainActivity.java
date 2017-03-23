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
//        Sequence sequence = new Sequence(10);
//        for (int i=0; i<10; i++){
//            sequence.add(new Outer("outer"+i));
//        }
//
//        Selector selector = sequence.selector();
//        while(!selector.end()) {
//            msgToShow+=(selector.current() + "\n");
//            selector.next();
//        }

        //practice10.3
//        Outer outer = new Outer("I'm OuterClass");
//        Outer.Inner inner = outer.newInner();
//        msgToShow += inner.toString();

        //practice10.4
//        Sequence sequence = new Sequence(10);
//        Sequence.SequenceSelector selector = (Sequence.SequenceSelector)sequence.selector();
//        Sequence sequence1 = selector.Outer();
//        for (int i=0; i<10; i++){
//            sequence1.add(new Outer("outer"+i));
//        }

//        //practice10.5
//        Outer outer = new Outer("Outer");
//        Outer.Inner inner = outer.new Inner();
//        inner.toString();

        //practice10.6
        //默认构造函数与内部类一样是有protected权限控制，
        //所以这里声明一个public的构造函数，以便在ClassThree中能创建内部类对象。详见OutClassTwo


        //practice10.7
        E07practiceClass practice7 = new E07practiceClass();
        practice7.useInnerClass();

        //practice10.8
        //详见E07practiceClass。外部类可通过内部类对象访问其私有元素

        //practice10.9
//        E09practiceInterface.E09practiceClass e09 = new E09practiceInterface.E09practiceClass();
//        E09practiceInterface e09interface = e09.f();
//        e09interface.f();

        //practice10.10
//        E10practiceInterface e10interface = new E10practiceInterface();
//        E10practiceInterface e10interface1 = e10interface.fun();
//        E10practiceInterface e10interface2 = e10interface.f();


        //practice10.11
//        E11practiceClass e11practiceClass = new E11practiceClass();
//        Forcall forcall = e11practiceClass.getForcall();
//        // E11practiceClass.E11InnerClass e = (E11practiceClass.E11InnerClass)forcall;








        msgTextView.setText(msgToShow);
    }
}
