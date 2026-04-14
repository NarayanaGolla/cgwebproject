package com.cog.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class RegisterTest {

  private static WireMockServer wireMockServer;

    @BeforeAll
    static void setUp() {
//    wireMockServer = new WireMockServer(8089); // Start server on port 8089
//    wireMockServer.start();
//    WireMock.configureFor("localhost", 8089);

      wireMockServer =
              new WireMockServer(
                      options().port(8089).usingFilesUnderClasspath("wiremock")); // Local mock server
      wireMockServer.start();
  }

  @Test
  void testStub() {
    wireMockServer.stubFor(
        get("/hello").willReturn(aResponse().withStatus(200).withBody("Hello JUnit5")));
    String resp = RestAssured.get("http://localhost:8089/hello").asString();
    assertEquals("Hello JUnit5", resp);
    wireMockServer.verify(getRequestedFor(urlEqualTo("/hello")));
  }

    @Test
    void testStubFromMapping() {
        String resp = RestAssured
                .get("http://localhost:8089/sample/hello")
                .asString();

        assertEquals("Hello JUnit5", resp);

        wireMockServer.verify(getRequestedFor(urlEqualTo("/sample/hello")));
    }
}
