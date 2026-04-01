package com.webapp.junit5;

/**
 * Execution Flow (Guaranteed Order) @BeforeAll @BeforeEach @Test
 * with @Order(1) @AfterEach @BeforeEach @Test with @Order(2) @AfterEach @BeforeEach @Test
 * with @Order(3) @AfterEach @AfterAll
 */

/**
 * 1. @BeforeAll (only once)
 *
 * <p>For each test: 2. @BeforeEach 3. @Test 4. @AfterEach
 *
 * <p>5. @AfterAll (only once)
 */
public class Junit5ExecutionOrderTest {}
