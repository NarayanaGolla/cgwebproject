package com.webapp.steps;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

  private static final String HOST = "http://localhost:";
  private static final int PORT = 8089;
  private static WireMockServer server;

  @Before
  public void checkHealthEndpointOnce() {
    server =
        new WireMockServer(
            options().port(PORT).usingFilesUnderDirectory("src/test/resources/wiremock"));
    server.start();
  }

  @After
  public void teardown() {
    server.stop();
  }

  @Given("user calculated {int}\\/{int}")
  public void user_calculated(Integer int1, Integer int2) {
    int i = 1 / 0;
    System.out.println(i);
  }

  @Given("user is on login page")
  public void user_is_on_login_page() {
    System.out.println("Hello");

    given()
        .baseUri(HOST + PORT)
        .when()
        .get("/api/user/123")
        .then()
        .statusCode(201)
        .body("name", equalTo("John Doe"));
  }

  @When("user enters valid username and password")
  public void user_enters_valid_username_and_password() {}

  @When("clicks on login button")
  public void clicks_on_login_button() {}

  @Then("user is navigated to the home page")
  public void user_is_navigated_to_the_home_page() {}
}
