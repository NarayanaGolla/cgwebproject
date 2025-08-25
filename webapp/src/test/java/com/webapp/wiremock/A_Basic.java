package com.webapp.wiremock;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class A_Basic {

  // @Test
  public void testOne() {
    RestAssured.given().get("http://localhost:8080/users/1").then().assertThat().statusCode(200);
  }

  // @Test
  public void testTwo() {
    RestAssured.given().get("http://localhost:8080/users/2").then().assertThat().statusCode(201);
  }

  @Test
  public void testResponseHeaders() {
    String contentType =
        RestAssured.given()
            .get("http://localhost:8080/users/3")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .header("Content-Type");

    System.out.println("testResponseHeaders " + contentType);
    Assert.assertEquals(contentType, "text/plan");
  }
}
