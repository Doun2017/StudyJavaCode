//: net/mindview/util/ProcessFiles.java
//package net.mindview.util;
package com.wsn.chapter18javaio;

import java.io.*;
import java.util.regex.Pattern;

public class ProcessFiles {
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
//    private String ext;
    private String regex;
    private Pattern pattern;

    public ProcessFiles(Strategy strategy, String regex) {
        this.strategy = strategy;
//        this.ext = ext;
        pattern = Pattern.compile(regex);
        this.regex = regex;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) processDirectoryTree(new File("."));
            else for (String arg : args) {
                File fileArg = new File(arg);
                if (fileArg.isDirectory())
                    processDirectoryTree(fileArg);
                else { /* Allow user to leave off extension:*/
                    if (pattern.matcher(arg).matches())
                        strategy.process(new File(arg).getCanonicalFile());

//                    if (!arg.endsWith("." + ext)) arg += "." + ext;
//                    strategy.process(new File(arg).getCanonicalFile());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(root.getAbsolutePath(), regex))
            strategy.process(file.getCanonicalFile());
    } /* Demonstration of how to use it:*/

    public static void main(String[] args) {
        new ProcessFiles(new ProcessFiles.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
        }, ".*.java").start(args);
    }
} /* (Execute to see output) *//*:~*/