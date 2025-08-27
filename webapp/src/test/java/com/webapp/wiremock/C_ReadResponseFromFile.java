package com.webapp.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class C_ReadResponseFromFile {

  private static final String HOST = "localhost";
  private static int PORT = 8089;

  private static WireMockServer server;

  @BeforeClass
  public void initializeWireMock() {
    System.out.println("in beforeClass");

    System.out.println("in beforeClass");

    server = new WireMockServer(options().dynamicPort());
    server.start();

    PORT = server.port();
    WireMock.configureFor(HOST, PORT);

    System.out.println("WireMock started on port: " + PORT);

    ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
    mockResponse.withStatus(201);
    mockResponse.withBodyFile("get-user.json");

    WireMock.givenThat(WireMock.get("/emps/1").willReturn(mockResponse));
  }

  // test case 1
  @Test
  public void testCode() {

    String testApi = "http://localhost:" + PORT + "/emps/1";
    System.out.println("Service to be hit : " + testApi);

    // Response response =
    //   RestAssured.given().get(testApi).then().statusCode(201).extract().response();

    System.out.println();

    // Assert.assertEquals(response.getHeader("token"), "111");
  }

  @AfterClass
  public void closeServer() {
    if (server != null && server.isRunning()) {
      server.stop(); // use stop() instead of shutdown()
    }
  }
}
