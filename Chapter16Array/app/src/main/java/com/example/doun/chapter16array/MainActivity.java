package com.example.doun.chapter16array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.example.doun.chapter16array.ArrayOptions.print;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //practice 16.1
//        BerylliumSphere[] d = {new BerylliumSphere(),
//                new BerylliumSphere(), new BerylliumSphere()
//        };
//        showBerylliumSphere(d);
//        //不能使用聚集初始化
//        //showBerylliumSphere({new BerylliumSphere(),
//      //          new BerylliumSphere(), new BerylliumSphere()
//      //  });
//      //结论：You must use aggregate initialization at the point of an array variable’s
//      //definition. With dynamic initialization you can create and initialize an array
//      //object anywhere

        //practice 16.2
//        showBerylliumSphere(createBerylliumSphere(12));

        //practice 16.3
//        printDoubles(createDoubles(5,4,1.9,48.9));

//        //practice 16.4
//        printDoubles3(createDoubles3(5,4,2,1.9,48.9));


//        //practice 16.5
//        if (arr == null){
//            Log.e(TAG, "arr is null");
//        }

//        //practice 16.6
//        Log.e(TAG, Arrays.deepToString(creatBerylliumSphere(2,6)));

//        //practice 16.7
//        Log.e(TAG, Arrays.deepToString(creatBerylliumSphere(2,6,3)));

//        //practice 16.8
//        ArrayOfGenericType<String> arrayOfGenericType = new ArrayOfGenericType<>(3);

//        //practice 16.9
//        //! Peel<Banana> bananaPeel[] = new Peel<Banana>[10];
//        ArrayList<Peel<Banana>> peels = new ArrayList<>(10);
//        peels.add(new Peel<Banana>(1));
//        Peel<Banana> peel = peels.get(0);

        //practice 16.10 见类ChangedArrayOfGenericType


//        //practice 16.11
//        int a[] = new int[4];
//        Integer b[] = new Integer[3];
//        //!a = b;
//        //!b = a;

//        //practice 16.12
//        double d[] = new double[10];
//        CountingGenerator.Double g = new CountingGenerator.Double();
//        for (int i=0; i<10; i++){
//            d[i] = g.next();
//        }
//        Log.e(TAG, Arrays.toString(d));

//        //practice 16.13
//        char d[] = new char[10];
//        CountingGenerator.Character g = new CountingGenerator.Character();
//        for (int i=0; i<10; i++){
//            d[i] = g.next();
//        }
//        Log.e(TAG, new String(d));

        //practice 16.14 与练习12重复 略
        //practice 16.15 see ContainerComparison.java
        //practice 16.16 see SkipGenerator.java

//        //practice 16.17
//        int size = 6;
//        java.math.BigDecimal[] a1 = Generated.array(java.math.BigDecimal.class, new CountingGenerator.BigDecimal(), size);
//        print("a1 = " + Arrays.toString(a1));

//        //practice 16.18
//        BerylliumSphere berylliumSpheres1[] = new BerylliumSphere[]{new BerylliumSphere(),new BerylliumSphere(),
//                new BerylliumSphere(),new BerylliumSphere(),new BerylliumSphere()};
//        BerylliumSphere berylliumSpheres2[] = new BerylliumSphere[berylliumSpheres1.length];
//        System.arraycopy(berylliumSpheres1, 0, berylliumSpheres2, 0, berylliumSpheres1.length);
//        print("berylliumSpheres1 = " + Arrays.toString(berylliumSpheres1));
//        print("berylliumSpheres2 = " + Arrays.toString(berylliumSpheres2));//打印出的对象id都一样，证明了是浅拷贝

//        //practice 16.19
//        Practice19 p1[] = new Practice19[]{new Practice19(1), new Practice19(2), new Practice19(3)};
//        Practice19 p2[] = new Practice19[]{new Practice19(1), new Practice19(2), new Practice19(3)};
//        Log.e(TAG, "result = " + Arrays.equals(p1, p2));

//        //practice 16.20
//        Practice19 p1[][] = new Practice19[][]{{new Practice19(1)}, {new Practice19(2)}, {new Practice19(3)}};
//        Practice19 p2[][] = new Practice19[][]{{new Practice19(1)}, {new Practice19(2)}, {new Practice19(3)}};
//        Log.e(TAG, "result = " + Arrays.deepEquals(p1, p2));

//        //practice 16.21
//        BerylliumSphere berylliumSpheres1[] = new BerylliumSphere[]{new BerylliumSphere(),new BerylliumSphere(),
//                new BerylliumSphere(),new BerylliumSphere(),new BerylliumSphere()};
//        Log.e(TAG, "berylliumSpheres1 = " + Arrays.toString(berylliumSpheres1));
//        Arrays.sort(berylliumSpheres1);
//        Log.e(TAG, "after sort ：：berylliumSpheres1 = " + Arrays.toString(berylliumSpheres1));


//        //practice 16.22
//        int arrayi[] =  new int[]{2,4,5,1,3};
//        Log.e(TAG, "pos = " + Arrays.binarySearch(arrayi, 3));//输出竟然是-2，果然在未排序数组中查找是不可预知的。
//
//        int arrayi[] =  new int[]{1,2,3,4,5};
//        Log.e(TAG, "pos = " + Arrays.binarySearch(arrayi, 3));//输出竟然是-2，果然在未排序数组中查找是不可预知的。
//

//        //practice 16.23
//        Generator<Integer> gen = new SkipGenerator.Integer(2);
//        Integer[] a = Generated.array(new Integer[25], gen);
//        print("before Sorted array: " + Arrays.toString(a));
//        Arrays.sort(a, Collections.reverseOrder());
//        print("Sorted array: " + Arrays.toString(a));

        //practice 16.23
        Practice19 arrayi[] =  new Practice19[]{new Practice19(2),new Practice19(1),
                new Practice19(3),new Practice19(5),new Practice19(4)};
        Comparator<Practice19> comparator = new Comparator<Practice19>() {
            @Override
            public int compare(Practice19 o1, Practice19 o2) {
                if (o1.getMyi()==o2.getMyi()) return 0;
                return o1.getMyi()>o2.getMyi()?1:-1;
            }
        };
        Arrays.sort(arrayi, comparator);
        Log.e(TAG, "pos = " + Arrays.binarySearch(arrayi, new Practice19(2), comparator));











    }
    int arr[][];

    public class ArrayOfGenericType<T> {
        T[] array; // OK
        //practice 16.8 去掉注解将会产生unchecked cast警告
        //@SuppressWarnings("unchecked")
        public ArrayOfGenericType(int size) {
            //! array = new T[size]; // Illegal
            array = (T[])new Object[size]; // "unchecked" Warning
        }
        // Illegal:
        //! public <U> U[] makeArray() { return new U[10]; }
    } ///:~

    public class ChangedArrayOfGenericType<T> {
        List<T> array;
        public ChangedArrayOfGenericType(int size) {
            array = new ArrayList<>(size);
        }
        // Illegal:
        //! public <U> U[] makeArray() { return new U[10]; }
    } ///:~

    public class Banana{}
    public class Peel<T> {
        T[] array; // OK
        //        //practice 16.8 然并无
        @SuppressWarnings("unchecked")
        public Peel(int size) {
            //! array = new T[size]; // Illegal
            array = (T[])new Object[size]; // "unchecked" Warning
        }
        // Illegal:
        //! public <U> U[] makeArray() { return new U[10]; }
    } ///:~

    public BerylliumSphere[][] creatBerylliumSphere(int lines, int rows){
        BerylliumSphere [][]berylliumSpheres = new BerylliumSphere[lines][rows];
        for (int i=0;i<lines;i++){
            for (int j=0;j<rows;j++){
                berylliumSpheres[i][j]=new BerylliumSphere();
            }
        }
        return berylliumSpheres;
    }
    public BerylliumSphere[][][] creatBerylliumSphere(int lines, int rows, int zs){
        BerylliumSphere [][][]berylliumSpheres = new BerylliumSphere[lines][rows][zs];
        for (int i=0;i<lines;i++){
            for (int j=0;j<rows;j++){
                for (int k=0;k<zs;k++){
                    berylliumSpheres[i][j][k]=new BerylliumSphere();
                }
            }
        }
        return berylliumSpheres;
    }

    public void showBerylliumSphere(BerylliumSphere[] berylliumSpheres){
        for (BerylliumSphere b:berylliumSpheres){
            Log.d(TAG, b.toString());
        }
    }
    public BerylliumSphere[] createBerylliumSphere(int size){
        BerylliumSphere berylliumSphere[] = new BerylliumSphere[size];
        for (int i=0;i<size;i++){
            berylliumSphere[i] = new BerylliumSphere();
        }
        return berylliumSphere;
    }

    public void printDoubles(double[][] doubles){
        StringBuilder builder=new StringBuilder();

        for (double[] doubles1:doubles){
            builder.append(Arrays.toString(doubles1));
            builder.append("\n");
        }

        Log.d(TAG, builder.toString());
    }

    public double[][] createDoubles(int lines, int lows, double min, double max){
        if (!(min < max))
            throw new IllegalArgumentException("BadRange");

        double [][]doubles = new double[lines][lows];
        for (int i=0;i<lines;i++){
            for (int j=0;j<lows;j++){
                double d = min+ Math.random()*(max-min);
                doubles[i][j]=d;
            }
        }
        return doubles;
    }


    public void printDoubles3(double[][][] doubles){
        Log.d(TAG, Arrays.deepToString(doubles));

//        for (double[][] doubles2:doubles){
//            for (double[] doubles1:doubles2){
//                Log.d(TAG, Arrays.toString(doubles1));
//            }
//        }
    }

    public double[][][] createDoubles3(int lines, int lows,int z, double min, double max){
        if (!(min < max))
            throw new IllegalArgumentException("BadRange");

        double [][][]doubles = new double[lines][lows][z];
        for (int i=0;i<lines;i++){
            for (int j=0;j<lows;j++){
                for (int k=0;k<z;k++){
                    double d = min+ Math.random()*(max-min);
                    doubles[i][j][k]=d;
                }
            }
        }
        return doubles;
    }
}
