package com.common.wiremock; // package com.wiremock;
//
// import static com.github.tomakehurst.wiremock.client.WireMock.*;
//
// import com.cog.utils.JsonUtils;
// import com.github.tomakehurst.wiremock.WireMockServer;
// import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Test;
//
//
// import java.io.IOException;
// import java.net.HttpURLConnection;
// import java.net.ProtocolException;
// import java.net.URL;
// import java.util.Scanner;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
//
// public class WireMockTest {
//
//    private WireMockServer wireMockServer;
//
//    @BeforeClass
//    void setup() {
//        wireMockServer = new WireMockServer(
//                WireMockConfiguration.wireMockConfig()
//                        .port(8080)  // Start on port 8080
//                        .usingFilesUnderClasspath("wiremock")  // ✅ Load stubs from
// `src/test/resources/wiremock`
//        );
//        wireMockServer.start();
//    }
//
//    @AfterClass
//    void teardown() {
//        wireMockServer.stop();
//    }
//
//    @Test
//    void testStubbedUser() throws IOException {
//        // ✅ Make HTTP request to WireMock
//        URL url = new URL("http://localhost:8080/users/123");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        // ✅ Read response
//        Scanner scanner = new Scanner(connection.getInputStream());
//        String responseBody = scanner.useDelimiter("\\A").next();
//        scanner.close();
//
//        // ✅ Validate response
//        String expectedResponse = "{\"id\":123,\"name\":\"John
// Doe\",\"email\":\"john.doe@example.com\"}";
//        assertEquals(200, connection.getResponseCode());
//        // Use `similar()` for deep comparison
//
// assertTrue(JsonUtils.convertJSONStringtoJSONObject(expectedResponse).similar(JsonUtils.convertJSONStringtoJSONObject(responseBody)), "JSONs are not equal!");
//
//        verify(getRequestedFor(urlEqualTo("/api/users")));
//       // verifyThat(getRequestedFor(urlEqualTo("/api/users")));
//
//
//    }
// }
