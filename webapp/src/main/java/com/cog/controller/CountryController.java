package com.cog.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // allow frontend requests
public class CountryController {

  @GetMapping("/countries")
  public List<Map<String, String>> getCountriesAndStates() {
    return Arrays.asList(
        Map.of("code", "IN", "name", "India"),
        Map.of("code", "US", "name", "USA"),
        Map.of("code", "CA", "name", "Canada"));
  }

  @GetMapping("/states/{countryCode}")
  public List<Map<String, String>> getStates(@PathVariable String countryCode) {
    if (countryCode.equalsIgnoreCase("IN")) {
      return List.of(
          Map.of("code", "AP", "name", "Andhra Pradesh"),
          Map.of("code", "KA", "name", "Karnataka"));
    } else if (countryCode.equalsIgnoreCase("US")) {
      return List.of(
          Map.of("code", "CA", "name", "California"), Map.of("code", "TX", "name", "Texas"));
    } else {
      return List.of();
    }
  }
}
