package com.cog.java8features.JavaMethodReferences.Constructor;

import java.util.function.Supplier;

class Person {
    public Person() { }
}

public class NoargumentConstructor {
    public static void main(String[] args) {
        Supplier<Person> supplier = Person::new;
        Person p = supplier.get();
    }
}
