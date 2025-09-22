package com.cog.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cog.*")
@ComponentScan(basePackages = "com.cog.*")
@EntityScan(basePackages = "com.cog.*")
@EnableJpaRepositories(basePackages = "com.cog.*")
public class SpringBootLauncer implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootLauncer.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringBootLauncer.class, args);
  }

  @Override
  public void run(String... args) {
    LOGGER.info("argument size");
  }
}
