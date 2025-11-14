package com.cog.service;

import com.cog.dom.Course;
import com.cog.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  @Autowired private CourseRepository repository;

  public Course saveCourse(Course course) {
    return repository.save(course);
  }

  public List<Course> getAllCourses() {
    return repository.findAll();
  }
}
