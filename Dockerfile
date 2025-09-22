# Use a base image with Java 17
FROM openjdk:17-jdk-alpine

# Add metadata
LABEL maintainer="yourname@example.com"

# Set working directory inside container
WORKDIR /app

# Accept JAR file as build argument
ARG JAR_FILE=webapp/build/libs/webapp-1.0.0.jar

# Copy JAR file to container
COPY ${JAR_FILE} app.jar

# Optional: copy logging properties if you have it
# COPY logging.properties /app/logging.properties

# Expose ports (Spring Boot default is 8080)
EXPOSE 8080

# JVM options (optional)
# ENV JAVA_OPTS="-Djava.util.logging.config.file=/app/logging.properties"

# Run the JAR
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
#ENTRYPOINT ["java", "-jar", "app.jar"]