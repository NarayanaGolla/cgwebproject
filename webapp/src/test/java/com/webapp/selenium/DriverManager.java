package com.webapp.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver() {
        driver.set(new ChromeDriver());
    }

    public static void setDriver(String browser) {
        if (browser.equals("chrome")) {
            driver.set(new ChromeDriver());
        } else if (browser.equals("firefox")) {
            driver.set(new FirefoxDriver());
        }
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
