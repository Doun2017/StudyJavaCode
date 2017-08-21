//: net/mindview/util/OSExecute.java
// Run an operating system command
// and send the output to the console.
package com.wsn.chapter18javaio;
//package net.mindview.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OSExecute {
    public static List<String> command(String command) {
        List<String> stringList = new ArrayList<>();
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = results.readLine()) != null) {
//                System.out.println(s);
                stringList.add(s);
            }

            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // Report errors and return nonzero value
            // to calling process if there are problems:
            while ((s = errors.readLine()) != null) {
                System.err.println(s);
                stringList.add(s);
                if (!s.contains("����")) {
                    err = true;
                }
            }
        } catch (Exception e) {
            // Compensate for Windows 2000, which throws an
            // exception for the default command line:
            if (!command.startsWith("CMD /C"))
                command("CMD /C " + command);
            else
                throw new RuntimeException(e);
        }
        if (err)
            throw new OSExecuteException("Errors executing " + command);

        return stringList;
    }
} ///:~
