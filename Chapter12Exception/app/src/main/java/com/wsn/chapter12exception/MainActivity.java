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
        try{
            throw new MyLogException1("MyLogException1");
        } catch (MyLogException1 e){
            e.printStackTrace();
        }

        try{
            throw new MyLogException2(55);
        } catch (MyLogException2 e){
            e.printStackTrace();
        }
    }
}


//
//class MyException extends Exception{
//    String str;
//    MyException(String string){
//        super(string);
//        str = string;
//    }
//
//    public void showStr(){
//        Log.d("MyException", str);
//    }
//}


class MyLogException1 extends Exception{
    MyLogException1(String string){
        super(string);
        Log.d("MyLogException", string);
    }
}



class MyLogException2 extends Exception{
    MyLogException2(int i){
        super("code = "+i);
        Log.d("MyLogException", "code = "+i);
    }
}