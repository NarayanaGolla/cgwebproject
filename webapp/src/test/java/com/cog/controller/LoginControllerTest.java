package com.cog.controller;

import static io.restassured.RestAssured.given;

import com.cog.webapp.SpringBootLauncer;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
    classes = SpringBootLauncer.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginControllerTest {

  @LocalServerPort private int port;

  @BeforeEach
  void setup() {
    RestAssured.baseURI = "http://localhost:" + port;
    RestAssured.requestSpecification =
        new RequestSpecBuilder().setContentType(ContentType.JSON).build();
  }

  // ---------- Reusable JSON payloads ----------

  private String registerPayload(String username, String password) {
    return """
                {
                  "username": "%s",
                  "password": "%s",
                  "roles": ["ROLE_USER", "ROLE_ADMIN"]
                }
                """
        .formatted(username, password);
  }

  private String loginPayload(String username, String password) {
    return """
                {
                  "username": "%s",
                  "password": "%s"
                }
                """
        .formatted(username, password);
  }

  // ---------- Reusable POST helper ----------

  private Response post(String endpoint, String body) {
    return given().body(body).when().post(endpoint).then().log().all().extract().response();
  }

  // ---------- Tests ----------

  @Test
  @Order(1)
  void testRegisterUser() {
    SoftAssertions softly = new SoftAssertions();

    post("/register", registerPayload("john", "john123")).then().statusCode(201);
    // UserResponseValidator.validateUserResponse(response, softly);

    // softly.assertAll();

    // softly.assertThat(response.statusCode()).isEqualTo(201);

    // softly.assertThat(response.jsonPath().getInt("id")).isEqualTo(123);
    // softly.assertThat(response.jsonPath().getString("name")).isEqualTo("John");
    // softly.assertThat(response.jsonPath().getBoolean("active")).isTrue();

    // softly.assertAll();  // collect all failures
  }

  @Test
  @Order(2)
  void testLoginUser() {
    post("/login", loginPayload("john", "john123")).then().statusCode(200);
  }

  @Test
  @Order(3)
  void testExtractTokenAndUse() {
    String token =
        post("/login", loginPayload("john", "john123")).jsonPath().getString("accessToken");

    Assertions.assertNotNull(token);

    // Example: calling a secured API using extracted token
    //        given()
    //                .header("Authorization", "Bearer " + token)
    //                .when()
    //                .get("/secure/api")
    //                .then()
    //                .statusCode(200)
    //                .log().all();
  }
}
