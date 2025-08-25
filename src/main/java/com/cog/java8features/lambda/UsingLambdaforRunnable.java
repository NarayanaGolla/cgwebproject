package com.cog.java8features.lambda;
//Before Java 8

class LambdaExample {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from Runnable!");
            }
        };
        new Thread(r).start();
    }
}

//With Java 8 Lambda
class LambdaExample1 {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello from Lambda Runnable!");
        new Thread(r).start();
    }
}


public class UsingLambdaforRunnable {
}
