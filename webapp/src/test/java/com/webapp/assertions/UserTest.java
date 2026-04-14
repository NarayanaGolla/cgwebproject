package com.webapp.assertions;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void testUserValidation() {
    SoftAssertions softly = new SoftAssertions();

    // User user = new User("John", 16, "johnexample.com");

    // SoftAssertionsHelper.validateUser(user, softly);

    // softly.assertAll();   // 🔥 collects all failures

    //        UserAssert.assertThat(user)
    //                .hasValidName()
    //                .hasValidEmail();
  }
}
