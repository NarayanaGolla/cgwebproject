package com.webapp.springboot;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cog.jwt.AuthRequestDTO;
import com.cog.jwt.JwtService;
import com.cog.webapp.SpringBootLauncer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = SpringBootLauncer.class)
@AutoConfigureMockMvc
public class SpringBootLauncerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper; // For converting objects to JSON

  // 👇 Mock the dependencies inside your controller
  @MockitoBean private AuthenticationManager authenticationManager;
  @MockitoBean private JwtService jwtService;

  @Test
  void testLoginSuccess() throws Exception {

    // Arrange - mock authentication
    Authentication mockAuthentication = Mockito.mock(Authentication.class);
    when(mockAuthentication.isAuthenticated()).thenReturn(true);

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(mockAuthentication);

    when(jwtService.generateToken(eq("sankar"))).thenReturn("fake-jwt-token");

    // Request body
    var authRequest = new AuthRequestDTO();
    authRequest.setUsername("sankar");
    authRequest.setPassword("123456");

    // Act & Assert
    mockMvc
        .perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.accessToken").value("fake-jwt-token"));
  }

  @Test
  void testLoginFailure() throws Exception {
    // Arrange - simulate failed authentication
    Authentication mockAuthentication = Mockito.mock(Authentication.class);
    Mockito.when(mockAuthentication.isAuthenticated()).thenReturn(false);

    Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(mockAuthentication);

    AuthRequestDTO authRequest = new AuthRequestDTO();
    authRequest.setUsername("wrong");
    authRequest.setPassword("wrong");

    // Act & Assert
    mockMvc
        .perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
        .andExpect(status().is4xxClientError()); // Will throw UsernameNotFoundException
  }
}
