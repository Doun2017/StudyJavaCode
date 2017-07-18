//: generics/MultipleInterfaceVariants.java
// {CompileTimeError} (Won't compile)
package com.example.doun.chapter15generics;

interface Payable<T> {
}

class Employee implements Payable {
}

class Hourly extends Employee
        implements Payable {
} ///:~
