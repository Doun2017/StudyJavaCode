//: strings/Receipt.java
package com.wsn.chapter13string;

import java.util.*;

public class Receipt {
    private final int WIDTH = 20;
    private double total = 0;
    private Formatter f = new Formatter(System.out);

    public void printTitle() {
        String forStr = "%-"+WIDTH+"s %"+WIDTH+"s %"+WIDTH+"s\n";
        f.format(forStr, "Item", "Qty", "Price");
        f.format(forStr, "----", "---", "-----");
    }

    public void print(String name, int qty, double price) {
        String forStr = "%-"+WIDTH+".15s %"+WIDTH+"d %"+WIDTH+".2f\n";
        f.format(forStr, name, qty, price);
        total += price;
    }

    public void printTotal() {
        String forStr = "%-"+WIDTH+"s %"+WIDTH+"s %"+WIDTH+".2f\n";
        String forStr1 = "%-"+WIDTH+"s %"+WIDTH+"s %"+WIDTH+"s\n";
        f.format(forStr, "Tax", "", total * 0.06);
        f.format(forStr1, "", "", "-----");
        f.format(forStr, "Total", "", total * 1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
} /* Output:
Item              Qty      Price
----              ---      -----
Jack's Magic Be     4       4.25
Princess Peas       3       5.10
Three Bears Por     1      14.29
Tax                         1.42
                           -----
Total                      25.06
*///:~
