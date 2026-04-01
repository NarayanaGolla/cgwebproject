package com.cog.controller;

import com.cog.bean.RegisterBean;
import com.cog.dom.User;
import com.cog.jwt.AuthRequestDTO;
import com.cog.jwt.JwtResponseDTO;
import com.cog.jwt.JwtService;
import com.cog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  private static final String PATH ="(?:/register|/login)";
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public LoginController(
      UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  // Simple test endpoint
  @GetMapping("/greet")
  public ResponseEntity<String> greet(@RequestParam String name) {
    return ResponseEntity.ok("Hello, " + name);
  }

  // User Registration
  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterBean registerBean) {

    LOGGER.info("Register request for user: {}", registerBean.getUsername());

    User savedUser = userService.registerUser(registerBean);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }

  // User Login + JWT Generate
  @PostMapping("/login")
  public ResponseEntity<JwtResponseDTO> login(
      @Valid @RequestBody AuthRequestDTO authRequestDTO, HttpServletRequest request)
      throws Exception {

    LOGGER.info("Login attempt for user: {}", authRequestDTO.getUsername());

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(), authRequestDTO.getPassword()));

    if (!authentication.isAuthenticated()) {
      throw new UsernameNotFoundException("Invalid username or password");
    }

    String token = jwtService.generateToken(authRequestDTO.getUsername());
    // SAVE TOKEN IN SESSION
    request.getSession().setAttribute("authToken", token);

    JwtResponseDTO response = JwtResponseDTO.builder().accessToken(token).build();

    return ResponseEntity.ok(response);
  }
}
