package com.webapp.client;

import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

public class RestTemplateBasicAuthExample {
  public static void main(String[] args) {
    String url = "https://api.example.com/data";

    RestTemplate restTemplate = new RestTemplate();

    // Add Basic Auth credentials
    restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("username", "password"));

    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    System.out.println("Response: " + response.getBody());
  }
}
