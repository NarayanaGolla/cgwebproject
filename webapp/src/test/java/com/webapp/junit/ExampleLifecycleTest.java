package com.webapp.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

/*
Runs once before all tests.
Runs before each test.
Runs after each test.
Runs before each test.
Runs after each test.
Runs once after all tests.

 */

class ExampleLifecycleTest {

  @BeforeAll
  static void setupAll() {
    System.out.println("Runs once before all tests.");
  }

  @BeforeEach
  void setup() {
    System.out.println("Runs before each test.");
  }

  @Test
  void firstTest() {
    assertEquals(10, 5 + 5);
  }

  @Test
  void secondTest() {
    assertEquals("HELLO", "hello".toUpperCase());
  }

  @AfterEach
  void tearDown() {
    System.out.println("Runs after each test.");
  }

  @AfterAll
  static void tearDownAll() {
    System.out.println("Runs once after all tests.");
  }
}
