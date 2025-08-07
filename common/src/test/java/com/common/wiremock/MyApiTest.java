package com.common.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyApiTest {

  WireMockServer wireMockServer;

  @BeforeEach
  void startWireMock() {
    wireMockServer = new WireMockServer(8080);
    wireMockServer.start();

    configureFor("localhost", 8080);

    // Define stub:
    stubFor(
        get(urlEqualTo("/hello"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"message\":\"Hello, World!\"}")));
  }

  @AfterEach
  void stopWireMock() {
    wireMockServer.stop();
  }

  @Test
  void testMyApiCall() throws URISyntaxException, IOException, InterruptedException {
    // Call your code that hits http://localhost:8080/hello
    // and assert the result.
    HttpClient client = HttpClient.newBuilder().build();

    HttpRequest request =
        HttpRequest.newBuilder().uri(new URI("http://localhost:8080/hello")).GET().build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println(response.body());

    // Prints:
    // {"message":"Hello, World!"}
  }
}
