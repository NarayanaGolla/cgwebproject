package com.cog.controller;

import com.cog.dom.Course;
import com.cog.service.CourseService;
import com.cog.services.ReportService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/app")
public class ReportController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

  private ReportService reportService;
  @Autowired private CourseService service;

  @Autowired
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping("/userdata")
  public List<String> readUserData() {
    return reportService.readUserData();
  }

  @GetMapping("/courses")
  public List<Course> coursesData() {
    return service.getAllCourses();
  }

  // Save employee
  @PostMapping("/courses")
  public Course saveEmployee(@RequestBody Course course) {
    return service.saveCourse(course);
  }
}
