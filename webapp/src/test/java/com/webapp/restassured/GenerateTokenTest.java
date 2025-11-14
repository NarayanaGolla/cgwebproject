package com.webapp.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import com.cog.webapp.SpringBootLauncer;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SpringBootTest(
    classes = SpringBootLauncer.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GenerateTokenTest extends AbstractTestNGSpringContextTests {

  @LocalServerPort private int port;
  private static RequestSpecification requestSpec;

  @BeforeTest(enabled = false)
  public void beforeTest() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  @BeforeClass
  public void setup() {
    requestSpec =
        new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(port)
            .setContentType("application/json")
            .build();
  }

  @Test(enabled = false)
  public void user_login_test() {
    given()
        .contentType("application/json")
        .body("{\"username\":\"sankar\",\"password\":\"123456\"}")
        .when()
        .post("/login")
        .then()
        .statusCode(201)
        .body("accessToken", notNullValue()) // token should exist
        .body(
            "accessToken",
            matchesPattern(
                "^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$")); // JWT pattern
  }

  @Test
  public void user_login_test_spec() {
    given()
        .spec(requestSpec) // 👈 reuse the configuration
        .body("{\"username\":\"sankar\",\"password\":\"123456\"}")
        .when()
        .post("/login")
        .then()
        .statusCode(201)
        .body("accessToken", notNullValue())
        .body(
            "accessToken",
            matchesPattern("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$"));
  }

  @Test
  public void user_login_test_log() {
    given()
        .spec(requestSpec)
        .log()
        .all() // 👈 logs full request (headers, body, URI, etc.)
        .body("{\"username\":\"sankar\",\"password\":\"123456\"}")
        .when()
        .post("/login")
        .then()
        .log()
        .all() // 👈 logs full response (status, headers, body, etc.)
        .statusCode(201)
        .body("accessToken", notNullValue())
        .body(
            "accessToken",
            matchesPattern("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$"));
  }
}
