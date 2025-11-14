package com.webapp.client;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBearerExample {
  public static void main(String[] args) {
    String url = "https://api.example.com/users";
    String token = "your-jwt-access-token";

    WebClient webClient =
        WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .build();

    String response =
        webClient
            .get()
            .retrieve()
            .bodyToMono(String.class)
            .block(); // blocking for demo; avoid in reactive apps

    System.out.println("Response: " + response);
  }
}
