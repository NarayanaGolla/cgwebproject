package com.webapp.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.Matchers.*;

public class WireMockExampleTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(options()
                .port(8089)
                .usingFilesUnderClasspath("wiremock")
        ); // Local mock server
        wireMockServer.start();

        System.out.println("✅ WireMock started at: http://localhost:8089");
    }


    @Test
    void testRegisterAPI() {
        String requestBody = "{ \"username\": \"narayana\", \"password\": \"test123\" }";

        RestAssured.given()
                .baseUri("http://localhost:8089")
                .contentType("application/json")
                .body(requestBody)
     .when()
                .post("/api/register")
     .then()
                .statusCode(201)
                .body("status", equalTo("success"))
                .body("message", equalTo("User registered successfully"))
                .body("user.name", equalTo("Narayana"));
    }

    @AfterAll
    static void teardown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
