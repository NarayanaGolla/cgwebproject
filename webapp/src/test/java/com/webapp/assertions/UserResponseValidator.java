package com.webapp.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

public class UserResponseValidator {

  public static void validateUserResponse(Response response, SoftAssertions softly) {

    softly.assertThat(response.statusCode()).as("status code").isEqualTo(200);

    softly.assertThat(response.jsonPath().getInt("id")).as("id").isEqualTo(123);

    softly.assertThat(response.jsonPath().getString("name")).as("name").isEqualTo("John");

    softly.assertThat(response.jsonPath().getBoolean("active")).as("active").isTrue();
  }
}
