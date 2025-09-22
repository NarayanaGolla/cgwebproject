package com.cog.controller;

import com.cog.support.AppProperties;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeatureController {

  private final AppProperties appProperties;

  public FeatureController(AppProperties appProperties) {
    this.appProperties = appProperties;
  }

  @GetMapping("/features")
  public List<String> getFeatures() {
    return appProperties.getFeatures();
  }
}
