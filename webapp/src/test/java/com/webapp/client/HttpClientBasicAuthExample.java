package com.webapp.client;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class HttpClientBasicAuthExample {
  public static void main(String[] args) throws Exception {
    // Create credentials provider
    BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
    credsProvider.setCredentials(
        new AuthScope("example.com", 80),
        new UsernamePasswordCredentials("username", "password".toCharArray()));

    try (CloseableHttpClient httpClient =
        HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build()) {

      HttpGet request = new HttpGet("http://example.com/api/data");

      var response = httpClient.execute(request);
      String responseBody = EntityUtils.toString(response.getEntity());
      System.out.println("Response: " + responseBody);
    }
  }
}
