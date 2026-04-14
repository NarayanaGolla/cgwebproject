package com.webapp.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WireMockCompleteExampleTest {

  private WireMockServer wireMockServer;

  @BeforeEach
  void setUp() {
    wireMockServer = new WireMockServer(8089); // Start server on port 8089
    wireMockServer.start();
    WireMock.configureFor("localhost", 8089);

    // ---- STUB 1: Simple GET ----
    stubFor(
        get(urlEqualTo("/hello")).willReturn(aResponse().withStatus(200).withBody("Hello World!")));

    // ---- STUB 2: GET with JSON ----
    stubFor(
        get(urlEqualTo("/user/1"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"id\":1,\"name\":\"Narayana\"}")));

    // ---- STUB 3: POST with Request Body Matching ----
    stubFor(
        post(urlEqualTo("/login"))
            .withRequestBody(equalToJson("{\"username\":\"admin\",\"password\":\"1234\"}"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"token\":\"abc123\"}")));

    // ---- STUB 4: With Delay ----
    stubFor(
        get(urlEqualTo("/slow"))
            .willReturn(aResponse().withFixedDelay(1500).withBody("Delayed response")));
  }

  @AfterEach
  void tearDown() {
    wireMockServer.stop();
  }

  @Test
  void testWireMockStubs() {
    // ---- Call the stubbed endpoint ----
    String response = simpleHttpGet("http://localhost:8089/hello");
    assertEquals("Hello World!", response);

    String userResponse = simpleHttpGet("http://localhost:8089/user/1");
    assertTrue(userResponse.contains("Narayana"));
  }

  // ---- Simple HTTP GET method ----
  private String simpleHttpGet(String url) {
    try {
      java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
      java.net.http.HttpRequest request =
          java.net.http.HttpRequest.newBuilder().uri(java.net.URI.create(url)).build();

      java.net.http.HttpResponse<String> response =
          client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

      return response.body();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
