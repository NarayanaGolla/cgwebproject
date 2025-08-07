package com.cog.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.cog.*")
@ComponentScan(basePackages = "com.cog.*")
@EntityScan(basePackages = "com.cog.*")
public class SpringBootLauncer {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootLauncer.class, args);
  }
}
