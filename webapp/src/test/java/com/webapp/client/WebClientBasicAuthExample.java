package com.webapp.client;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBasicAuthExample {
  public static void main(String[] args) {
    String url = "https://api.example.com/data";

    WebClient webClient =
        WebClient.builder()
            .baseUrl(url)
            .defaultHeaders(headers -> headers.setBasicAuth("username", "password"))
            .build();

    String response = webClient.get().retrieve().bodyToMono(String.class).block();

    System.out.println("Response: " + response);
  }
}
