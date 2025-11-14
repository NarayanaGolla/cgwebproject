package com.webapp.junit5;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExecutionProcedureFileJunit5 {

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
