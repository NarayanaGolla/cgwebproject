# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Add a label (optional)
LABEL maintainer="yourname@example.com"

ENV JAVA_OPTS="-Djava.util.logging.config.file=/app/logging.properties"


ARG JAR_FILE=webapp/build/libs/webapp.jar

# Copy JAR file
COPY ${JAR_FILE} app.jar

# Run the JAR
ENTRYPOINT ["java","-jar","/app.jar"]