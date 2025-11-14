package com.cog.consumers;

import com.cog.bean.CourseBean;
import com.cog.bean.TraineeBean;
import com.cog.repository.JsonDatabase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  private JsonDatabase jsonDatabase;

  @Autowired
  public KafkaConsumer(JsonDatabase jsonDatabase) {
    this.jsonDatabase = jsonDatabase;
  }

  @KafkaListener(topics = "my-topic", groupId = "my-group")
  public void listen(String message) {
    System.out.println("Received message: " + message);
  }

  @KafkaListener(topics = "my-topic", groupId = "my-consumer-group")
  public void consume(String message) throws JsonProcessingException {
    System.out.println("Consumed message: " + message);

    // Check if message contains commas
    if (message == null || !message.contains(",")) {
      System.out.println("⚠️ Invalid message: " + message);
      return;
    }

    // Split into parts
    String[] parts = message.split(",");

    // Validate number of columns (expecting 4)
    if (parts.length != 4) {
      System.out.println("Invalid CSV format: " + message);
      return;
    }

    int id = Integer.parseInt(parts[0].trim());
    String name = parts[1].trim();
    String email = parts[2].trim();
    int age = Integer.parseInt(parts[3].trim());

    TraineeBean traineeBean = new TraineeBean(id, name, email, age);

    // ✅ Convert to JSON
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(traineeBean);

    System.out.println("JSON: " + jsonString);

    traineeBean.setId(id);
    traineeBean.setName(name);
    traineeBean.setEmail(email);
    traineeBean.setAge(age);
    jsonDatabase.saveUser(traineeBean);
    System.out.println("Consumed trainee: " + traineeBean);
  }

  @KafkaListener(topics = "my-topic", groupId = "my-course-group")
  public void consumeCourse(String message) throws JsonProcessingException {
    System.out.println("Consumed message: " + message);

    // Check if message contains commas
    if (message == null || !message.contains(",")) {
      System.out.println("⚠️ Invalid message: " + message);
      return;
    }

    // Split into parts
    String[] parts = message.split(",");

    // Validate number of columns (expecting 4)
    if (parts.length != 3) {
      System.out.println("Invalid CSV format: " + message);
      return;
    }

    Long courseId = Long.parseLong(parts[0].trim());
    String courseName = parts[1].trim();
    String duration = parts[2].trim();

    CourseBean courseBean = new CourseBean();
    courseBean.setCourseid(courseId);
    courseBean.setCoursename(courseName);
    courseBean.setDuration(duration);

    // ✅ Convert to JSON
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(courseBean);

    System.out.println("JSON: " + jsonString);

    jsonDatabase.saveCourse(courseBean);
    System.out.println("Consumed trainee: " + courseBean);
  }
}
