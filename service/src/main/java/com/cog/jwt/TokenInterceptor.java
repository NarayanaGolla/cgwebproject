package com.cog.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements ClientHttpRequestInterceptor, HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    System.out.println("Incoming request: " + request.getRequestURI());

    String token = (String) request.getSession().getAttribute("authToken");

    if (token != null && !token.isEmpty()) {
      // Add token to the request for internal use
      request.setAttribute("Authorization", "Bearer " + token);
    }

    return true; // continue request
  }

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

    // If an incoming auth should be preferred, components calling RestTemplate should set
    // the header on the request or pass via context. Here we prefer jwtResponseDTO if available.

    return execution.execute(request, body);
  }
}
