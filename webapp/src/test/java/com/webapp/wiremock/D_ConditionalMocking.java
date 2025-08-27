package com.webapp.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class D_ConditionalMocking {

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

    ResponseDefinitionBuilder mockResponse1 = new ResponseDefinitionBuilder();
    mockResponse1.withStatus(503);
    mockResponse1.withHeader("Content-Type", "text/html");
    mockResponse1.withBody("Service Not Available");

    ResponseDefinitionBuilder mockResponse2 = new ResponseDefinitionBuilder();
    mockResponse2.withStatus(200);
    mockResponse2.withHeader("Content-Type", "application/json");
    mockResponse2.withBody("{\"Current-Status\":\"running\"}");

    stubFor(
        WireMock.get("/emps/1")
            .withHeader("Accept", matching("text/plain"))
            .willReturn(mockResponse1));
    stubFor(
        WireMock.get("/emps/1")
            .withHeader("Accept", matching("application/json"))
            .willReturn(mockResponse2));

    //       stubFor(get(urlEqualTo("/emps/1")).withHeader("Accept" , matching("text/plain"))
    //               .willReturn(aResponse().withStatus(200).withHeader("Accept" ,
    // "application/json")
    //                       .withBody("{\"Current-Status\":\"running\"}").withFixedDelay(2500)));
    //

  }

  // test case 1
  @Test
  public void testCode1() {

    String testApi = "http://localhost:" + PORT + "/emps/1";
    System.out.println("Service to be hit : " + testApi);

    Response response =
        RestAssured.given().header(new Header("Content-Type", "text/html")).when().get(testApi);

    System.out.println();

    Assert.assertEquals(response.statusCode(), HttpStatus.SC_SERVICE_UNAVAILABLE);
  }

  @Test
  public void testCode2() {

    String testApi = "http://localhost:" + PORT + "/emps/1";
    System.out.println("Service to be hit : " + testApi);

    Response response =
        RestAssured.given()
            .header(new Header("Content-Type", "application/json"))
            .when()
            .get(testApi);

    System.out.println();

    Assert.assertEquals(response.statusCode(), HttpStatus.SC_SERVICE_UNAVAILABLE);
  }

  @AfterClass
  public void closeServer() {
    if (server != null && server.isRunning()) {
      server.stop(); // use stop() instead of shutdown()
    }
  }
}
