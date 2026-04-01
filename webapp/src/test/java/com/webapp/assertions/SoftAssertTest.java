package com.webapp.assertions;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class SoftAssertTest {

  @Test
  void testSoftAssertions() {
    SoftAssertions softly = new SoftAssertions();

    // softly.assertThat(5).isEqualTo(10); // fails
    // softly.assertThat("hello").startsWith("x"); // fails
    // softly.assertThat(true).isFalse(); // fails
    // softly.assertAll(); // 🔥 reports all failures together
  }
}
