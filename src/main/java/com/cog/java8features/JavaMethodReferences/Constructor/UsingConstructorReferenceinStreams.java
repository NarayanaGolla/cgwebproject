package com.cog.java8features.JavaMethodReferences.Constructor;

import java.util.List;

public class UsingConstructorReferenceinStreams {
    public static void main(String[] args) {
        List<String> names = List.of("Ram", "Krishna", "Narayana");

        List<Person1> people = names.stream()
                .map(Person1::new)  // calls Person(String name)
                .toList();
    }
}
