package com.webapp.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestTemplateBearerExample {
  public static void main(String[] args) {
    String url = "https://api.example.com/users";
    String token = "your-jwt-access-token";

    RestTemplate restTemplate = new RestTemplate();

    // Set headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + token);
    headers.set("Accept", "application/json");

    HttpEntity<String> entity = new HttpEntity<>(headers);

    // Make GET request
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    System.out.println("Response: " + response.getBody());
  }
}
