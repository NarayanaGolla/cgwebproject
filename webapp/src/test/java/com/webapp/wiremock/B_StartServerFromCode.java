package com.webapp.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class B_StartServerFromCode {

  private static final String HOST = "localhost";
  private static final int PORT = 8080;

  private static WireMockServer server;

  @BeforeClass
  public void initializeWireMock() {
    System.out.println("in beforeClass");

    server = new WireMockServer(PORT);
    server.start();
    WireMock.configureFor(HOST, PORT);

    //    ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
    //    mockResponse.withStatus(201);
    //    mockResponse.withStatusMessage("Hello Guys");
    //    mockResponse.withHeader("Content-Type", "text/json");
    //    mockResponse.withHeader("token", "111");
    //    mockResponse.withHeader("Set-Cookie", "111");
    //    mockResponse.withHeader("Set-Cookie", "111");
    //    mockResponse.withBody("text to put in the Body");
    //
    //    WireMock.givenThat(WireMock.get("/emps/1").willReturn(mockResponse));
  }

  // test case 1
  @Test
  public void closeServer() {
    if (server.isRunning() && null != server) {
      server.shutdown();
    }
  }

  @AfterClass
  public void afterClass() {
    System.out.println("in afterClass");
  }
}
