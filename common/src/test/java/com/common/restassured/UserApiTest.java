package com.common.restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class UserApiTest {

  @Disabled
  @Test
  void testGetUser() {
    given()
        .spec(Specs.requestSpec)
        .when()
        .get("/users/1")
        .then()
        .statusCode(200)
        .body("username", equalTo("Bret"));
  }

  @Disabled
  @Test
  void testGetUser12() {
    given().spec(Specs.requestSpec).when().get("/users/1").then().spec(Specs.responseSpec);
  }
}
