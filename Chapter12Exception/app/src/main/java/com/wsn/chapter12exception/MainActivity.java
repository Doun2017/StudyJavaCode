package com.wsn.chapter12exception;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //practice12.1
//        try{
//            throw new Exception("message");
//
//        } catch (Exception e){
//            Log.d(TAG, e.getMessage());
//
//        }finally {
//            Log.d(TAG, "finally executed");
//        }

        //practice12.2
//        String str=null;
//        try{
//            Log.d(TAG, str);
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        //practice12.3 12.7
//        //ArrayList<String> array = new ArrayList<>();
//        String array[] = new String[2];
//        try{
//            String str = array[2];
//        }catch (ArrayIndexOutOfBoundsException e){
//            e.printStackTrace();
//        }

        //practice12.4
//        try{
//            throw new MyException("message");
//        } catch (MyException e){
//            e.showStr();
//            Log.d(TAG, e.getMessage());
//            e.printStackTrace();
//        }


        //practice12.5
//        String array[] = new String[]{"abc", "def"};
//        int len=10;
//        while (len >= 0){
//            try{
//                String str = array[len];
//                Log.d(TAG, str+len);
//                break;
//            }catch (ArrayIndexOutOfBoundsException e){
//                e.printStackTrace();
//            }finally {
//                len--;
//            }
//        }

        //practice12.6
//        try{
//            throw new MyLogException1("MyLogException1");
//        } catch (MyLogException1 e){
//            e.printStackTrace();
//        }
//
//        try{
//            throw new MyLogException2(55);
//        } catch (MyLogException2 e){
//            e.printStackTrace();
//        }

        //practice12.8
//        try{
//            throwFun();
//        } catch (MyException e){
//            e.printStackTrace();
//        }

        //practice12.9
//        try{
//            throw3Exception(2);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //practice12.10
//        try{
//            f();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //practice12.11
//        try{
//            f11();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //practice12.12
//        Sequence sequence = new Sequence(3);
//        sequence.add(new String("a"));
//        sequence.add(new String("b"));
//        sequence.add(new String("c"));
//        sequence.add(new String("d"));

        //practice12.13
//        String str=null;
//        try{
//            Log.d(TAG, str);
//        } catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            Log.d(TAG, "finally");
//        }

        //practice12.14
//        f14(true); //不会打印"release source"

        //practice12.15
//        f15(true);  //会打印"release source"


        //practice12.16
//        f16();


        //practice12.17
//        f17();


//        String str=null;
//        try{
//            Log.d(TAG, str);
//        }finally {
//            return;
//        }

        //practice12.18
//        f18();

        //practice12.19
//        f19_1();
//        f19_2();

        //practice12.20
//        try {
//            StormyInning si = new StormyInning();
////            si.atBat();
//            si.addFunc();
////            si.event();
//        } catch (PopFoul e) {
//            System.out.println("Pop foul");
//        } catch (RainedOut e) {
//            System.out.println("Rained out");
//        } catch (BaseballException e) {
//            System.out.println("Generic baseball exception");
//        } catch (UmpireArgument e){
//            System.out.println("StormyInning  UmpireArgument exception");
//        }
//
//        // Strike not thrown in derived version.
//        try {
//            // What happens if you upcast?
//            Inning i = new StormyInning();
////            i.atBat();
////            i.event();
//
//            i.addFunc();
//
//            // You must catch the exceptions from the
//            // base-class version of the method:
//        } catch (Strike e) {
//            System.out.println("Strike");
//        } catch (Foul e) {
//            System.out.println("Foul");
//        } catch (RainedOut e) {
//            System.out.println("Rained out");
//        } catch (BaseballException e) {
//            System.out.println("Generic baseball exception");
//        }catch (UmpireArgument e){
//            System.out.println("Inning  UmpireArgument exception");
//        }

        //practice12.21
        //因为派生类构造器必须要抛出基类构造器声明所抛出的异常，所以派生类构造器永远无法捕获此异常

        //practice12.22
//        for (int i=0;i<5;i++){
//            try{
//                FailingConstructor failingConstructor = new FailingConstructor(i);
//                try{
//                    failingConstructor.doSomething();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    failingConstructor.dispose();
//                }
//            }catch (Exception e){
//                Log.e("MainActivity", " practice12.22 "+i);
//                e.printStackTrace();
//            }
//        }

        //practice12.23 24
//        for (int i=0;i<5;i++){
//            for (int j=4;j>=0;j--) {
//                try {
//                    FailingConstructor1 failingConstructor1 = new FailingConstructor1(i, j);
//                    try {
//                        failingConstructor1.doSomething();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        failingConstructor1.dispose();
//                    }
//                } catch (FirstException e) {
//                    Log.e("MainActivity", " practice12.23  FirstException  " + i + "  " + j);
//                    e.printStackTrace();
//                } catch (SecondException e) {
//                    Log.e("MainActivity", " practice12.23  SecondException  " + i + "  " + j);
//                    e.printStackTrace();
//                }finally {
//                    //nothing to do
//                }
//            }
//        }

        //practice12.25
//        ClassA c = new ClassC();
//        try {
//            c.throwSomething();
//        } catch (BottomLevelException e) {
//            e.printStackTrace();
//        } catch (MidLevelException e) {
//            e.printStackTrace();
//        } catch (TopLevelException e) {
//            e.printStackTrace();
//        }

        //practice12.26
//        Exception in thread "main" java.io.FileNotFoundException: MainException.java (系统找不到指定的文件。)
//        at java.io.FileInputStream.open0(Native Method)
//        at java.io.FileInputStream.open(FileInputStream.java:195)
//        at java.io.FileInputStream.<init>(FileInputStream.java:138)
//        at java.io.FileInputStream.<init>(FileInputStream.java:93)
//        at com.wsn.chapter12exception.MainException.main(MainException.java:10)
//        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//        at java.lang.reflect.Method.invoke(Method.java:498)
//        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)

        //practice12.27
//        String array[] = new String[2];
//        try{
//            String str = array[2];
//        }catch (ArrayIndexOutOfBoundsException e){
//            throw new RuntimeException(e);
//        }

        //practice12.28
//        throw new MyException1("message");

        //practice12.29
        StormyInning29 si = new StormyInning29();
        si.atBat();

        Inning29 i = new StormyInning29();
        i.atBat();

    }


    private void f19_1() {
            try {
                LostMessage1 lm = new LostMessage1();
                try {
                    lm.f();
                } catch (Exception e) {
                    Log.e("LostMessage", e.toString());
                }finally {
                    lm.dispose();
                }
            } catch (Exception e) {
//            System.out.println(e);
                Log.e("LostMessage", e.toString());
            }
    }
    private void f19_2() {
            try {
                LostMessage1 lm = new LostMessage1();
                try {
                    lm.f();
                } finally {
                    try {
                        lm.dispose();
                    }catch (Exception e) {
                        Log.e("LostMessage", e.toString());
                    }
                }
            } catch (Exception e) {
//            System.out.println(e);
                Log.e("LostMessage", e.toString());
            }
    }


    private void f18() {
        try {
            LostMessage lm = new LostMessage();
            try {
                try {
                    lm.f();
                } finally {
                    lm.dispose();
                }
            }finally {
                lm.antherDispose();
            }
        } catch(Exception e) {
            Log.e("LostMessage", e.toString());
        }
    }

    private void f17() {
        Frog frog = new Frog();
        try {
            // Code and exception handling...
            return;
        } finally {
            Log.e("Frog", "Bye!");
            frog.dispose();
        }


    }
    private void f16() {
        CADSystem x = new CADSystem(47);
        try {
            // Code and exception handling...
            return;
        } finally {
            x.dispose();
        }
    }



        //会抛出抛出RuntimeException 并且不会打印"release source"
    private void f14(boolean throwRuntimeException){
        try{
            if (throwRuntimeException){
                //抛出RuntimeException
                String str=null;
                Log.d(TAG, str);
            }

            g();
            Log.e(TAG, "release source");
        }catch (MyLogException1 exception1){
            exception1.printStackTrace();
            Log.e(TAG, "release source");
        }

    }

    //会抛出抛出RuntimeException 但是会打印"release source"
    private void f15(boolean throwRuntimeException){
        try{
            if (throwRuntimeException){
                //抛出RuntimeException
                String str=null;
                Log.d(TAG, str);
            }

            g();
            Log.e(TAG, "release source");
        }catch (MyLogException1 exception1){
            exception1.printStackTrace();
        }finally {
            Log.e(TAG, "release source");
        }
    }

    private void f11(){
        try{
            g();
        }catch (MyLogException1 exception1){
            throw new RuntimeException(exception1);
        }
    }

    private void f()throws MyLogException2{
        try{
            g();
        }catch (MyLogException1 exception1){
            throw new MyLogException2(12);
        }
    }

    private void g() throws MyLogException1{
        throw new MyLogException1("MyLogException1");
    }




    private void throwFun() throws MyException{
        throw new MyException("my Exception");
    }
    private void throw3Exception(int i) throws MyException, MyLogException1, MyLogException2{
        if (i<0)
            throw new MyException("my Exception");
        if (i==0)
            throw new MyLogException1("MyLogException1");
        if (i>0)
            throw new MyLogException2(22);
        return;
    }

}



class TopLevelException extends Exception{}
class MidLevelException extends TopLevelException{}
class BottomLevelException extends MidLevelException{}
class ClassA{
    void throwSomething() throws TopLevelException{
        throw new TopLevelException();
    }
}
class ClassB extends ClassA{
    void throwSomething() throws MidLevelException{
        throw new MidLevelException();
    }
}

class ClassC extends ClassB{
    void throwSomething() throws BottomLevelException{
        throw new BottomLevelException();
    }
}



class MyException extends Exception{
    String str;
    MyException(String string){
        super(string);
        str = string;
    }

    public void showStr(){
        Log.d("MyException", str);
    }
}

class MyException1 extends RuntimeException{
    String str;
    MyException1(String string){
        super(string);
        str = string;
    }

    public void showStr(){
        Log.d("MyException", str);
    }
}


class MyLogException1 extends Exception{
    MyLogException1(String string){
        super(string);
        Log.e("MyLogException1", string);
    }
}



class MyLogException2 extends Exception{
    MyLogException2(int i){
        super("code = "+i);
        Log.e("MyLogException2", "code = "+i);
    }
}