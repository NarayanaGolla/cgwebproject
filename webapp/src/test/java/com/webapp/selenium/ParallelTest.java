package com.webapp.selenium;
import org.testng.annotations.Test;

public class ParallelTest {

    @Test
    public void test1() throws InterruptedException {
        System.out.println("Test1 running on Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }

    @Test
    public void test2() throws InterruptedException {
        System.out.println("Test2 running on Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }

    @Test
    public void test3() throws InterruptedException {
        System.out.println("Test3 running on Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }
}
