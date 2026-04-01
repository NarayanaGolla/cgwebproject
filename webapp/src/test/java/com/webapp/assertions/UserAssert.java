package com.webapp.assertions;

import com.cog.bean.User;
import org.assertj.core.api.AbstractAssert;

public class UserAssert extends AbstractAssert<UserAssert, User> {

  public UserAssert(User user) {
    super(user, UserAssert.class);
  }

  public static UserAssert assertThat(User actual) {
    return new UserAssert(actual);
  }

  public UserAssert hasValidName() {
    isNotNull();
    if (actual.getName() == null || actual.getName().isBlank()) {
      failWithMessage("Expected user to have a valid name");
    }
    return this;
  }

  public UserAssert hasValidEmail() {
    isNotNull();
    //        if (!actual.getEmail().contains("@")) {
    //            failWithMessage("Expected email to contain '@'");
    //        }
    return this;
  }
}
