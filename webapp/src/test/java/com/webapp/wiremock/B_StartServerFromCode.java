package com.webapp.wiremock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class B_StartServerFromCode {

  private static final String HOST = "localhost";
  private static int PORT = 8089;

  private static WireMockServer server;

  @BeforeClass
  public void initializeWireMock() {

    server = new WireMockServer(options().port(PORT));
    server.start();
    WireMock.configureFor(HOST, PORT);

    ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
    mockResponse.withStatus(201);
    mockResponse.withStatusMessage("Hello Guys");
    mockResponse.withHeader("Content-Type", "text/json");
    mockResponse.withHeader("token", "111");
    mockResponse.withHeader("Set-Cookie", "111");
    mockResponse.withHeader("Set-Cookie", "111");
    mockResponse.withBody("text to put in the Body");

    mockResponse.withBodyFile("get-user.json");

    WireMock.stubFor(WireMock.get("/emps/1").willReturn(mockResponse));
    // WireMock.givenThat(WireMock.get("/emps/1").willReturn(mockResponse));
  }

  // test case 1
  @Test
  public void testCode() {

    String testApi = "http://localhost:" + PORT + "/emps/1";
    System.out.println("Service to be hit : " + testApi);

    Response response =
        RestAssured.given().get(testApi).then().statusCode(201).extract().response();

    Assert.assertEquals(response.getHeader("token"), "111");
  }

  @AfterClass
  public void closeServer() {
    if (server != null && server.isRunning()) {
      server.stop(); // use stop() instead of shutdown()
    }
  }
}
