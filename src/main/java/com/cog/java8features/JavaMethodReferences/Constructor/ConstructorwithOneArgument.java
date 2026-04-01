package com.cog.java8features.JavaMethodReferences.Constructor;

import java.util.function.Function;

class Person1 {
    public Person1(String name) { }
}

public class ConstructorwithOneArgument {
    public static void main(String[] args) {
        Function<String, Person1> func = Person1::new;
        Person1 p = func.apply("Narayana");
    }
}
