package com.cog.java8features.JavaMethodReferences.Constructor;

import java.util.function.BiFunction;

class Employee1 {
    public Employee1(String name, int age) { }
}

public class ConstructorwithTwoArguments {
    public static void main(String[] args) {

        BiFunction<String, Integer, Employee1> biFunc = Employee1::new;
        Employee1 e = biFunc.apply("Narayana", 30);
    }
}
