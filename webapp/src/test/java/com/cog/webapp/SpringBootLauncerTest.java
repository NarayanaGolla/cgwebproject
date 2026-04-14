package com.cog.webapp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootLauncer.class)
public class SpringBootLauncerTest {

  @Autowired private SpringBootLauncer launcher;

  @Test
  void testRunMethod() {
    assertDoesNotThrow(() -> launcher.run());
  }
}
