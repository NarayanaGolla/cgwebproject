package com.webapp.restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class GetAndPostExample {

  // @Test
  public void test_Get() {

    baseURI = "https://reqres.in/api/";

    given()
        .header("x-api-key", "reqres-free-v1") // Add header
        .header("Content-Type", "application/json") // You can add multiple headers
        .get("users?page=1")
        .then()
        .statusCode(200)
        .body("data[1].first_name", equalTo("Janet"))
        .body("data.first_name", hasItems("Charles"));
  }

  @Test
  public void test_Post() {

    Map<String, Object> map = new HashMap<String, Object>();

    map.put("name", "Raghav");
    map.put("job", "Teacher");

    System.out.println(map);

    JSONObject request = new JSONObject();

    request.put("name", "Raghav");
    request.put("job", "Teacher");

    System.out.println(request);
    System.out.println(request.toJSONString());

    baseURI = "https://reqres.in/api/";

    given()
        .header("Content-Type", "application/json")
        .header("x-api-key", "reqres-free-v1")
        . // Add header
        contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(request.toJSONString())
        .when()
        .post("users")
        .then()
        .statusCode(201)
        .log()
        .all();
  }

  // @Test
  public void test_2_put() {

    JSONObject request = new JSONObject();

    request.put("name", "Raghav");
    request.put("job", "Teacher");

    System.out.println(request);
    System.out.println(request.toJSONString());

    given()
        .header("Content-Type", "application/json")
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(request.toJSONString())
        .when()
        .put("https://reqres.in/api/users")
        .then()
        .statusCode(200);
  }

  // @Test
  public void test_3_patch() {

    JSONObject request = new JSONObject();

    request.put("name", "Raghav");
    request.put("job", "Teacher");

    System.out.println(request);
    System.out.println(request.toJSONString());

    given()
        .header("Content-Type", "application/json")
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(request.toJSONString())
        .when()
        .patch("https://reqres.in/api/users")
        .then()
        .statusCode(200)
        .log()
        .all();
  }

  @Test
  public void test_4_delete() {
    when().delete("https://reqres.in/api/users/2").then().statusCode(401).log().all();
  }
}
