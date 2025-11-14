package com.webapp.junit5;

/**
 * Execution Flow (Guaranteed Order)
 *
 * @BeforeAll
 *
 * @BeforeEach
 *
 * @Test with @Order(1)
 *
 * @AfterEach
 *
 * @BeforeEach
 *
 * @Test with @Order(2)
 *
 * @AfterEach
 *
 * @BeforeEach
 *
 * @Test with @Order(3)
 *
 * @AfterEach
 *
 * @AfterAll
 */
public class Junit5ExecutionOrderTest {
}
