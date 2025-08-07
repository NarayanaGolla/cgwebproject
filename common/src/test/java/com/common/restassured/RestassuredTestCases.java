package com.common.restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class RestassuredTestCases {

  @Test(enabled = false)
  void createUser_shouldReturn201() {
    RestAssured.baseURI = "https://reqres.in";

    String json =
        """
        {
            "name": "narayana",
            "job": "developer"
        }
    """;

    given()
        .header("Content-Type", "application/json")
        .body(json)
        .when()
        .post("/api/users")
        .then()
        .statusCode(201)
        .body("name", equalTo("narayana"));

    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .header("Authorization", "Bearer abc123")
        .contentType(ContentType.JSON);

    given()
        .queryParam("postId", 1)
        .log()
        .all()
        .when()
        .get("/comments")
        .then()
        .log()
        .all()
        .statusCode(200)
        .body("size()", greaterThan(0));
  }

  @Test(enabled = false)
  void getUsers_shouldReturn200AndUserData() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    given()
        .when()
        .get("/users/1")
        .then()
        .statusCode(200)
        .body("username", equalTo("Bret")); // example assertion
  }
}
