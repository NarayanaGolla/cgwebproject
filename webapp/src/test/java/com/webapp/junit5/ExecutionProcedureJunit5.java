package com.webapp.junit5;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExecutionProcedureJunit5 {

  WireMockServer wireMockServer = null;

  @BeforeAll
  void setup() {
    System.out.println("setup");
    wireMockServer =
        new WireMockServer(
            options()
                .port(8080)
                .usingFilesUnderClasspath(
                    "wiremock") // ✅ load mappings + files from resources/wiremock
            );
    wireMockServer.start();

    configureFor("localhost", 8080);

    stubFor(
        get(urlEqualTo("/api/user/123"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withBodyFile("user-response.json")
                    .withHeader("Content-Type", "application/json")));
  }

  @AfterAll
  void teardown() {
    System.out.println("teardown");
    System.out.println(
        "Mappings loaded: " + wireMockServer.listAllStubMappings().getMappings().size());

    String response = given().when().get("http://localhost:8080/api/user/123").asString();
    System.out.println(response);

    wireMockServer.stop();
  }

  @Test
  void testCreateUser() {
    System.out.println("testCreateUser");
  }
}
