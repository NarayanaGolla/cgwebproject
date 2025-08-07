package com.common.restassured;

import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {
  public static RequestSpecification requestSpec =
      RestAssured.given()
          .baseUri("https://jsonplaceholder.typicode.com")
          .header("Authorization", "Bearer abc123")
          .contentType("application/json")
          .log()
          .all();

  public static ResponseSpecification responseSpec =
      new ResponseSpecBuilder()
          .expectStatusCode(200)
          .expectContentType("application/json")
          .expectBody("username", notNullValue())
          .build();

  public static RequestSpecification requestSpecEx =
      RestAssured.given()
          .baseUri("https://api.mycompany.com")
          .header("X-API-Key", "YOUR_API_KEY_VALUE")
          .contentType("application/json")
          .accept("application/json")
          .log()
          .all();

  public static ResponseSpecification responseSpec201 =
      new ResponseSpecBuilder().expectStatusCode(201).expectContentType("application/json").build();
}
