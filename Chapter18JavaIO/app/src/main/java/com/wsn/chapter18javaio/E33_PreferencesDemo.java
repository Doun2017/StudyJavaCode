package com.wsn.chapter18javaio;

/**
 * Created by power on 2017/8/23,023.
 */

//: io/E33_PreferencesDemo.java
// {RunByHand}
/****************** Exercise 33 *****************
 * Write a program that displays the current value
 * of a directory called "base directory" and
 * prompts you for a new value. Use the Preferences
 * API to store the value.
 ***********************************************/

import java.util.*;
import java.util.prefs.*;

//import static net.mindview.util.Print.*;

public class E33_PreferencesDemo {
    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(E33_PreferencesDemo.class);
        String directory = prefs.get("base directory", "Not defined");
        System.out.println("directory: " + directory);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a new directory: ");
        directory = sc.nextLine();
        prefs.put("base directory", directory);
        System.out.println("\ndirectory: " + directory);
    }
} ///:~
