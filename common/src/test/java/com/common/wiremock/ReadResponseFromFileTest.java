package com.common.wiremock;

import static org.hamcrest.Matchers.equalTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

public class ReadResponseFromFileTest {
  static WireMockServer wireMockServer;

  @BeforeAll
  static void setUp() {
    wireMockServer =
        new WireMockServer(
            WireMockConfiguration.wireMockConfig()
                .port(8080)
                .usingFilesUnderDirectory("src/test/resources"));
    wireMockServer.start();
  }

  @AfterAll
  static void tearDown() {
    wireMockServer.stop();
  }

  @Test
  void testHelloMapping() {
    RestAssured.get("http://localhost:8080/hello")
        .then()
        .statusCode(200)
        .body(equalTo("Hello from WireMock!"));
  }
}
