package com.cog.consumers;

import com.cog.bean.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JsonKafkaConsumer {

  @KafkaListener(topics = "json-topic", groupId = "json-group")
  public void consume(User user) {
    System.out.println("Received User: " + user.getName() + ", Age: " + user.getAge());
  }
}
