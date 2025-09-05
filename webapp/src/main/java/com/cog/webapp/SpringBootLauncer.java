package com.cog.webapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cog.*")
@ComponentScan(basePackages = "com.cog.*")
@EntityScan(basePackages = "com.cog.*")
@EnableJpaRepositories(basePackages = "com.cog.service.repository")
public class SpringBootLauncer implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootLauncer.class, args);
  }

  @Override
  public void run(String... args) throws Exception {}
}
