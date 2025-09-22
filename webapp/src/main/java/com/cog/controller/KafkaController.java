package com.cog.controller;

import com.cog.bean.User;
import com.cog.producer.JsonKafkaProducer;
import com.cog.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

  @Autowired private KafkaProducer producer;

  @Autowired private JsonKafkaProducer jsonKafkaProducer;

  @PostMapping("/publish")
  public ResponseEntity<String> publish(@RequestParam("message") String message) {
    producer.sendMessage("my-topic", message);
    return ResponseEntity.ok("Message sent to Kafka topic");
  }

  @PostMapping("/send")
  public ResponseEntity<String> sendUser(@RequestBody User user) {
    jsonKafkaProducer.sendUser(user);

    // Provide CSV file path
    producer.sendCsvToKafka("data.csv");

    return ResponseEntity.ok("User sent!");
  }
}
