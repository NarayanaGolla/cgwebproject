package com.cog.java8features.bifunction;

import java.util.function.BiFunction;
import java.util.function.Function;

 class LambdaBiFunctionExample {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        Function<Integer, String> toString = result -> "Result: " + result;

        System.out.println("Multiplication: " + multiply.apply(5, 3));
    }
}
