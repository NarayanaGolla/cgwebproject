package com.webapp.assertions;

import com.cog.bean.User;
import org.assertj.core.api.SoftAssertions;

public class SoftAssertionsHelper {

  public static void validateUser(User user, SoftAssertions softly) {
    softly.assertThat(user.getName()).isNotEmpty();
    softly.assertThat(user.getAge()).isGreaterThan(18);
    // softly.assertThat(user.getEmail()).contains("@");
  }
}
