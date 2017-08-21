//: io/OSExecuteDemo.java
// Demonstrates standard I/O redirection.
package com.wsn.chapter18javaio;

//import net.mindview.util.*;

import java.util.List;

public class OSExecuteDemo {
    public static void main(String[] args) {
        List<String> stringList = OSExecute.command("javap MainActivity");
        for (int i=0; i<stringList.size(); i++)
            System.out.println(stringList.get(i));
//        System.out.println(stringList.toString());
    }
} /* Output:
Compiled from "OSExecuteDemo.java"
public class OSExecuteDemo extends java.lang.Object{
    public OSExecuteDemo();
    public static void main(java.lang.String[]);
}
*///:~
