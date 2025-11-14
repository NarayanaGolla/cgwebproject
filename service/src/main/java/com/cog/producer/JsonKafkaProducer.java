package com.cog.producer;

import com.cog.bean.User;
import com.cog.dom.Course;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired private ObjectMapper objectMapper;

  public void sendUser(User user) {
    try {
      String userJson = objectMapper.writeValueAsString(user); // ✅ Convert to JSON
      kafkaTemplate.send("my-topic", userJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize user", e);
    }
  }

  public void sendCourse(Course course) {
    try {
      String courseJson = objectMapper.writeValueAsString(course); // ✅ Convert to JSON
      kafkaTemplate.send("my-topic", courseJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize user", e);
    }
  }
}
