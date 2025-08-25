package com.cog.java8features.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class LambdaPredicateExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Alex");

        // Using Predicate
        Predicate<String> startsWithA = name -> name.startsWith("A");

        List<String> filteredNames = names.stream()
                .filter(startsWithA)
                .collect(Collectors.toList());

        System.out.println(filteredNames);
    }
}


public class UsingPredicateforFilteringaList {
}
