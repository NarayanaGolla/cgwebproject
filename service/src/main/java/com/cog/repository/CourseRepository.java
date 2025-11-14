package com.cog.repository;

import com.cog.dom.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  // No extra methods required for basic CRUD
}
