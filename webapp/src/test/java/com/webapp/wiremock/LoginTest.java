package com.webapp.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
in beforeSuite
in beforeTest
in beforeClass
in beforeMethod
in test case 1
in afterMethod
in beforeMethod
in test case 2
in afterMethod
in afterClass
in afterTest
in afterSuite



 */

public class LoginTest {

  private static final Logger LOG = LoggerFactory.getLogger(LoginTest.class);

  @BeforeSuite
  public void beforeSuite() {
    System.out.println("in beforeSuite");
  }

  @BeforeClass
  public void initializeWireMock() {
    System.out.println("in beforeClass");
  }

  // test case 1
  @Test
  public void testCase1() {
    System.out.println("in test case 1");
  }

  // test case 2
  @Test
  public void testCase2() {
    System.out.println("in test case 2");
  }

  @BeforeMethod
  public void beforeMethod() {
    System.out.println("in beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
    System.out.println("in afterMethod");
  }

  @AfterClass
  public void afterClass() {
    System.out.println("in afterClass");
  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("in beforeTest");
  }

  @AfterTest
  public void afterTest() {
    System.out.println("in afterTest");
  }

  @AfterSuite
  public void afterSuite() {
    System.out.println("in afterSuite");
  }
}
