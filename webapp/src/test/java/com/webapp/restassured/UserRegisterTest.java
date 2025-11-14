package com.webapp.restassured;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cog.webapp.SpringBootLauncer;
import com.webapp.utils.ResourcesUtils;
import io.restassured.RestAssured;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;

@SpringBootTest(
    classes = SpringBootLauncer.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRegisterTest extends AbstractTestNGSpringContextTests {

  @LocalServerPort private int port;

  @BeforeTest
  public void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 9090;
  }

  @Test(enabled = false)
  public void user_register_test() throws IOException, URISyntaxException {

    String jsonBody = ResourcesUtils.loadResourceAsString("register_payload.json");

    given()
        .contentType("application/json")
        .body(jsonBody)
        .when()
        .post("/register")
        .then()
        .statusCode(200)
        .log()
        .all();
  }

  @Test(enabled = false)
  public void user_login_test() throws IOException, URISyntaxException {

    String jsonBody = ResourcesUtils.loadResourceAsString("register_payload.json");

    given()
            .contentType("application/json")
            .body(jsonBody)
            .when()
            .post("/login")
            .then()
            .statusCode(201)
            .log()
            .all();
  }

  @Test
  public void user_login_test_validation() throws IOException {

    String jsonBody = ResourcesUtils.loadResourceAsString("register_payload.json");

    Response response =  given()
            .contentType("application/json")
            .body(jsonBody)
            .when()
            .post("/login");

    assertEquals(201, response.getStatusCode());
  }
}
