package com.cog.controller;

import com.cog.service.bean.LoginBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

  @GetMapping("/")
  public String home() {
    return "Hello, World!";
  }

  @GetMapping("/greet")
  public String greet(@RequestParam String name) {
    return name;
  }

  @PostMapping(value = "/login", consumes = "application/json")
  public ResponseEntity<LoginBean> userRegister(@Validated @RequestBody LoginBean loginBean) {
    LOGGER.isDebugEnabled();
    return new ResponseEntity<>(loginBean, HttpStatus.CREATED);
  }
}
