package com.example.doun.chapter15generics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.doun.chapter15generics.pets.Dog;
import com.example.doun.chapter15generics.pets.Pet;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static com.example.doun.chapter15generics.Watercolors.*;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //practice15.1
//        Holder3<Pet> hTest = new Holder3<Pet>(new Dog());
//        System.out.println(hTest.get());

        //practice15.2
//        Holder3Objs<Pet> h3 = new Holder3Objs<Pet>(new Pet(),new Pet(),new Pet());
//        System.out.println(h3.getA());
//        System.out.println(h3.getB());
//        System.out.println(h3.getC());

        //practice15.3
//        System.out.println(k());

        //practice15.4
//        Sequence<String> sequence = new Sequence(10);
//        for (int i = 0; i < 10; i++)
//            sequence.add(Integer.toString(i));
//        Selector<String> selector = sequence.selector();
//        while (!selector.end()) {
//            System.out.print(selector.current() + " ");
//            Log.d(TAG, selector.current() + " ");
//            selector.next();
//        }

        //practice15.5 LinkedStack.java

        //practice15.6
//        RandomList<Integer> rs = new RandomList<>();
//        for (int i=0; i<20; i++)
//            rs.add(i);
//        for (int i = 0; i < 11; i++)
//            Log.d(TAG, rs.select() + " ");

        //practice15.7
//        for (int i : new IterableFibonacci(18))
//            Log.d(TAG, i + " ");

        //practice15.8
//        StoryCharactersGenerator gen = new StoryCharactersGenerator();
//        for (int i = 0; i < 5; i++)
//            Log.d(TAG, gen.next()+"");
//        for (StoryCharacters c : new StoryCharactersGenerator(5))
//            Log.d(TAG, c+"");

        //practice15.9
//        GenericMethods gm = new GenericMethods();
//        gm.g("", 123, 4.33);

        //practice15.10
//        GenericMethods gm = new GenericMethods();
//        gm.h("", 123, 4.33f);

        //practice15.11
//        Map<BadGays, List<String>> sls = New.map();
//        List<QingTianZhu> ls = New.list();
//        LinkedList<WeiZhenTian> lls = New.lList();
//        Set<StoryCharacters> ss = New.set();
//        Queue<DaHuangFeng> qs = New.queue();
//
//        //practice15.12
//        f(New.<QingTianZhu>list());

        //practice15.13
//        ArrayList<Integer> fnumbers = Generators.fill(new ArrayList<Integer>(), new Fibonacci(), 12);
//        for (int i : fnumbers)
//            Log.d(TAG, "ArrayList"+i + ", ");
//        Set<Integer> fSetNumbers = Generators.fill(new HashSet<Integer>(), new Fibonacci(), 12);
//        for (int i : fSetNumbers)
//            Log.d(TAG, i + ", ");

        //practice15.14
//        for(int i = 0; i < 5; i++)
//            System.out.println(new CountedObject());

        //practice15.15 不属实
//        TwoTuple<String, Integer> testTuple = (TwoTuple<String, Integer>)TupleTest2.f2();
//        System.out.println(testTuple);

        //practice15.16
//        System.out.println(TupleTest2.l());


        //practice15.17  关于set知之甚少，所以从习题答案抄来的
//        Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
//        Set<Watercolors> set2 = EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
//        System.out.println("set1: " + set1);
//        System.out.println("set2: " + set2);
//        System.out.println("union(set1, set2): " + Sets.union(set1, set2));
//        System.out.println("union(set1, set2) type: " +
//                Sets.union(set1, set2).getClass().getSimpleName());
//        Set<Integer> set3 = new HashSet<Integer>();
//        set3.add(1);
//        Set<Integer> set4 = new HashSet<Integer>();
//        set4.add(2);
//        System.out.println("set3: " + set3);
//        System.out.println("set4: " + set4);
//        System.out.println("union(set3, set4): " + Sets.union(set3, set4));
//        System.out.println("union(set3, set4) type: " +
//                Sets.union(set3, set4).getClass().getSimpleName());
//        //输出：
//        //System.out: union(set1, set2) type: MiniEnumSet
//        //System.out: set3: [1]
//        //System.out: set4: [2]
//        //System.out: union(set3, set4): [1, 2]
//        //System.out: union(set3, set4) type: HashSet

        //practice15.18  FishEatFish.java

        //practice15.19
//        System.out.println(new CargoShip(14, 5, 10));

        //practice15.20
//        func(new ClassHasThreeFunc());

        //practice15.21
//        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<Building>(Building.class);
//        ctt1.addType("string", String.class);
//        Float flo = (Float)ctt1.createNew("float");
//        String str = (String)ctt1.createNew("string");
//        if (str != null){
//            Log.d(TAG, "生成了String");
//        }

        //practice15.22
//        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<Building>(Building.class);
//        Building building = (Building) ctt1.c("new Building");
//        Log.d(TAG, building.toString());

        //practice15.23  FactoryConstraint.java

        //practice15.24
        ClassTypeCapture2<Building> ctt1 = new ClassTypeCapture2<>(Building.class);
        ctt1.addType("Building2", new Building2.Factory());
        Float flo = (Float)ctt1.createNew("float");
        Building2 building2 = (Building2)ctt1.createNew("Building2");
        if (building2 != null){
            Log.d(TAG, "生成了Building2");
        }





    }

    public static <E extends HasTwoFunc> void func(E t){
        t.func1();
        t.func2();
    }

    void f(List<QingTianZhu> list){}


    static SixTuple<String,String,String,Integer,Double,Float> k() {
        return new SixTuple<String,String,String,Integer,Double,Float>(
                "one","two", "hi", 47, 11.1, 2.656f);
    }


}

class CountedObject {
    private static long counter = 0;
    private final long id = counter++;
    public long id() { return id; }
    public String toString() { return "CountedObject " + id;}
} ///:~
