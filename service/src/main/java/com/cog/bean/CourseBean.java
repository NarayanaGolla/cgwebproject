package com.cog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseBean {

  private Long courseid;
  private String coursename;
  private String duration;
}
