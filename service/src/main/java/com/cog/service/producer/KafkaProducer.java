package com.cog.service.producer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;
  private final String TOPIC = "my-topic";

  public void sendMessage(String topic, String message) {
    kafkaTemplate.send(topic, message);
    kafkaTemplate.send(TOPIC, "partition-key", "Hello with key");
  }

  public void sendCsvToKafka(String fileName) {
    try {

      ClassPathResource resource = new ClassPathResource(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

      String line;
      boolean isFirstLine = true;

      while ((line = br.readLine()) != null) {
        // Skip CSV header
        if (isFirstLine) {
          isFirstLine = false;
          continue;
        }

        // Send each line to Kafka
        kafkaTemplate.send(TOPIC, line);
        System.out.println("Sent: " + line);
      }
      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
