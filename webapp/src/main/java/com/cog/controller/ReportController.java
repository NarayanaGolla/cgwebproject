package com.cog.controller;

import com.cog.services.ReportService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ReportController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

  private ReportService reportService;

  @Autowired
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping("/userdata")
  public List<String> readUserData() {
    return reportService.readUserData();
  }
}
