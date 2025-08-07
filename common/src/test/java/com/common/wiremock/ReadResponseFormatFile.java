package com.common.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.testng.Assert.assertEquals;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReadResponseFormatFile {

  private static WireMockServer wireMockServer;

  @BeforeClass
  public static void setup() {
    wireMockServer = new WireMockServer(8089); // Start WireMock on port 8089
    wireMockServer.start();

    configureFor("localhost", 8089);

    // Stub API response
    stubFor(
        get(urlEqualTo("/test"))
            .willReturn(aResponse().withStatus(200).withBody("Hello from WireMock")));
  }

  @Test
  public void testWireMock() throws IOException {
    String response = makeHttpCall("http://localhost:8089/test");
    assertEquals(response, "Hello from WireMock");
  }

  @AfterClass
  public static void tearDown() {
    if (wireMockServer != null) {
      wireMockServer.stop();
    }
  }

  // Simple HTTP GET request method
  private static String makeHttpCall(String urlString) throws IOException {
    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    Scanner scanner = new Scanner(conn.getInputStream());
    String response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
    scanner.close();

    return response;
  }
}
