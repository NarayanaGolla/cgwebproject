package com.webapp.selenium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.setDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
