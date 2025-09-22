package com.cog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// This class is a controller which means this will handle all the requests from the client
@Controller
public class HomeController {

  @GetMapping("/index") // This will handle all the request comming from the home page "/"
  public String index() {

    return "home"; // This is the view name, which refers to the Home.html view in
    // /src/main/resources/templates
    // return "Home Page - Public";

  }
}
