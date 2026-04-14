package com.webapp.selenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class GoogleTest  extends BaseTest {

    @Test
    public void testGoogle() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.google.com");
        System.out.println("Google Title: " + driver.getTitle() +
                " Thread: " + Thread.currentThread().getId());
    }

    @Test
    public void testBing() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.bing.com");
        System.out.println("Bing Title: " + driver.getTitle() +
                " Thread: " + Thread.currentThread().getId());
    }
}
