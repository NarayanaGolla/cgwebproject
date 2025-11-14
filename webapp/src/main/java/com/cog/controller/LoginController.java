package com.cog.controller;

import com.cog.bean.RegisterBean;
import com.cog.dom.User;
import com.cog.jwt.AuthRequestDTO;
import com.cog.jwt.JwtResponseDTO;
import com.cog.jwt.JwtService;
import com.cog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final HttpSession session;
  private final JwtService jwtService;

  public LoginController(
      UserService userService,
      AuthenticationManager authenticationManager,
      HttpSession session,
      JwtService jwtService) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.session = session;
    this.jwtService = jwtService;
  }

  @GetMapping("/greet")
  public String greet(@RequestParam String name) {
    return name;
  }

  @PostMapping(value = "/register", consumes = "application/json")
  public ResponseEntity<?> userRegister(
      @Validated @RequestBody RegisterBean registerBean, BindingResult result) {

    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
    }
    User registeredUser = userService.registerUser(registerBean);
    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping(value = "/login", consumes = "application/json")
  public ResponseEntity<JwtResponseDTO> userRegister(
      @Validated @RequestBody AuthRequestDTO authRequestDTO) throws Exception {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(), authRequestDTO.getPassword()));
    if (authentication.isAuthenticated()) {
      session.setAttribute("token", jwtService.generateToken(authRequestDTO.getUsername()));
      return new ResponseEntity<>(
          JwtResponseDTO.builder().accessToken(session.getAttribute("token").toString()).build(),
          HttpStatus.CREATED);
    } else {
      throw new UsernameNotFoundException("invalid user request..!!");
    }
  }
}
