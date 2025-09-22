package com.webapp.restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestsExample {

  @Test
  public void test_1() {
    System.out.println("Hello");
    Response response =
        RestAssured.given()
            .header("x-api-key", "reqres-free-v1") // Add header
            .header("Content-Type", "application/json") // You can add multiple headers
            .get("https://reqres.in/api/users?page=1");
    int statuscode = response.getStatusCode();

    System.out.println(statuscode);
    System.out.println(response.getTime());
    System.out.println(response.getBody().asString());
    System.out.println(response.getHeader("content-type"));

    Assert.assertEquals(statuscode, 200);
  }

  @Test
  public void test_2() {
    System.out.println("Hello");
    given()
        .header("x-api-key", "reqres-free-v1") // Add header
        .header("Content-Type", "application/json") // You can add multiple headers
        .get("https://reqres.in/api/users?page=1")
        .then()
        .statusCode(200)
        .body("data[1].id", equalTo(2));
  }
}
