package com.webapp.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class HttpClientBearerTokenExample {
  public static void main(String[] args) throws Exception {
    String token = "your-jwt-or-access-token";
    String url = "https://api.example.com/users";

    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet request = new HttpGet(url);

      // Attach Authorization header
      request.setHeader("Authorization", "Bearer " + token);
      request.setHeader("Accept", "application/json");

      var response = httpClient.execute(request);
      String responseBody = EntityUtils.toString(response.getEntity());
      System.out.println("Response: " + responseBody);
    }
  }
}
/*

<dependency>
  <groupId>org.apache.httpcomponents.client5</groupId>
  <artifactId>httpclient5</artifactId>
  <version>5.3.1</version>
</dependency>
 */
