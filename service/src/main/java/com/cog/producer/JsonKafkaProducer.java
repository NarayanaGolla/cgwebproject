package com.cog.producer;

import com.cog.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;

  //  @Bean
  //  public ProducerFactory<String, User> producerFactory() {
  //    Map<String, Object> config = new HashMap<>();
  //    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
  //    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
  //    config.put(
  //        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // ✅ JSON
  // serializer
  //    return new DefaultKafkaProducerFactory<>(config);
  //  }

  // @Bean
  //  public KafkaTemplate<String, User> kafkaTemplate() {
  //    return new KafkaTemplate<>(producerFactory());
  //  }

  @Autowired private ObjectMapper objectMapper;

  public void sendUser(User user) {
    try {
      String userJson = objectMapper.writeValueAsString(user); // ✅ Convert to JSON
      kafkaTemplate.send("my-topic", userJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize user", e);
    }
  }
}
