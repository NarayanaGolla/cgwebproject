package com.cog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// This class is a controller which means this will handle all the requests from the client
@Controller
public class HomeController {

  @GetMapping("/index")
  public String index() {
    return "home";
  }
}
