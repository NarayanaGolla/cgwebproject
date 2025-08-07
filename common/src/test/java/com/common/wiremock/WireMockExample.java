package com.common.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockExample {
  public static void main(String[] args) {
    // Initialize and start the WireMock server
    WireMockServer wireMockServer = new WireMockServer(8080);
    wireMockServer.start();
    WireMock.configureFor("localhost", 8080);

    // Stub an endpoint
    stubFor(get(urlEqualTo("/api/test")).willReturn(aResponse().withStatus(200)));

    // Simulate a request
    WireMock.get("http://localhost:8080/api/test");

    // List<ServeEvent> allRequests = wireMockServer.getServeEvents().getRequests();

    // Retrieve all received requests
    //        List<ServeEvent> allRequests = wireMockServer
    //                .getServeEvents()
    //                .getRequests();
    //
    //        // Print details of each request
    //        for (ServeEvent event : allRequests) {
    //            System.out.println("Request received at: " + event.getRequest().getUrl());
    //        }

    // Stop the WireMock server
    wireMockServer.stop();
  }
}
