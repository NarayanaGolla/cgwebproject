# Spring Boot Cucumber RestAssured Project

## Overview
This project is a basic Spring Boot application that leverages **Cucumber** and **Rest Assured** for API testing. It also generates test reports in multiple formats: **Pretty HTML**, **JSON**, and **Extent Reports**. The project is designed to be flexible and works with both **Maven** and **Gradle** build tools.

The integration allows seamless execution of tests with scenarios tagged by Azure DevOps (ADO) test case IDs. After execution, the results can be posted back to ADO for tracking.

## Key Features
- **Cucumber**: Behavior-driven development (BDD) tool for defining test scenarios in a human-readable format.
- **Rest Assured**: Library for testing RESTful APIs.
- **Reporting**: Generates Pretty HTML, JSON, and Extent Reports after test execution.
- **Maven and Gradle Support**: Build and run tests using either Maven or Gradle.
- **Azure DevOps Integration**: Easily extendable to push test results to ADO Test Plans.

## Project Structure
```
springboot-cucumber-restassured/
│
├── pom.xml                     # Maven build file
├── build.gradle                 # Gradle build file
├── src
│   ├── main
│   │   └── java/com/example/demo
│   │       └── DemoApplication.java  # Spring Boot main application class
│   ├── test
│   │   └── java/com/example/demo/steps
│   │       └── StepDefinitions.java  # Step definitions for Cucumber scenarios
│   └── test/resources
│       └── features
│           └── api_test.feature  # Cucumber feature file for testing
├── reports                      # Directory for generated test reports
└── README.md                    # Project overview and setup instructions
```
## Run Cucumber
```bash
# Build the project
./gradlew build

# Run the Cucumber tests
./gradlew test

./gradlew clean test --tests "com.webapp.runner.TestRunner"


```