package com.cog.java8features.lambda;

@FunctionalInterface
interface MyFunction {
    int add(int a, int b);
}

class LambdaFunctionalInterface {
    public static void main(String[] args) {
        MyFunction sum = (a, b) -> a + b;

        System.out.println("Sum: " + sum.add(10, 20));
    }
}


public class UsingLambdawithFunctionalInterface {
}
