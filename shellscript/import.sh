#!/bin/bash

echo "Starting the script..."

#set -e

# Function to find an available port
find_available_port() {
  local port=$1
  while lsof -i:$port >/dev/null 2>&1; do
    port=$((port+1))
  done
  echo $port
}

# Navigate to the React project directory
REACT_DIR="/d/github/web/my-react-app"
SPRING_RESOURCES_DIR="/d/github/web/app/src/main/resources"
SPRING_PROJECT_DIR="/d/github/web"
DEBUG_PORT=5006

if [ ! -d "$REACT_DIR" ]; then
  echo "Directory not found: $REACT_DIR"
  exit 1
fi

cd "$REACT_DIR" || { echo "Failed to change directory to $REACT_DIR"; exit 1; }

# Check if npm is available
if ! command -v npm &> /dev/null; then
  echo "npm is not installed or not in PATH"
  exit 1
fi

# Build the React application
if ! npm run build; then
  echo "React build failed"
  exit 1
fi

# Wait for 5 seconds
echo "Waiting for 5 seconds..."
sleep 5

# Navigate to the Spring Boot resources directory
if [ ! -d "$SPRING_RESOURCES_DIR" ]; then
  echo "Directory not found: $SPRING_RESOURCES_DIR"
  exit 1
fi

cd "$SPRING_RESOURCES_DIR" || { echo "Failed to change directory to $SPRING_RESOURCES_DIR"; exit 1; }

# Remove the existing 'public' directory and create a new one
if [ -d "public" ]; then
  if ! rm -r public; then
    echo "Failed to remove existing 'public' directory"
    exit 1
  fi
fi

if ! mkdir public; then
  echo "Failed to create 'public' directory"
  exit 1
fi

# Copy the React build output to the new 'public' directory
if ! cp -r "$REACT_DIR/build/"* public/; then
  echo "Failed to copy React build output"
  exit 1
fi

# Navigate to the Spring Boot project directory
if [ ! -d "$SPRING_PROJECT_DIR" ]; then
  echo "Directory not found: $SPRING_PROJECT_DIR"
  exit 1
fi

cd "$SPRING_PROJECT_DIR" || { echo "Failed to change directory to $SPRING_PROJECT_DIR"; exit 1; }

# Check if gradlew is available
if ! command -v ./gradlew &> /dev/null; then
  echo "Gradle Wrapper (./gradlew) is not found or not executable"
  exit 1
fi

# Find an available debug port starting from 5005
DEBUG_PORT=$(find_available_port 5005)

#./gradlew clean
#./gradlew build

# Run the Spring Boot application with debugging enabled using Gradle Wrapper
if ! ./gradlew clean bootRun -Dorg.gradle.jvmargs="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5006"; --stacktrace --info; then
  echo "Failed to run Spring Boot application with debugging on port $DEBUG_PORT"
  exit 1
fi

# Run the Spring Boot application with debugging enabled using Gradle Wrapper
# if ! ./gradlew bootRun -Dorg.gradle.jvmargs="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:$DEBUG_PORT" --stacktrace --info; then
 #  echo "Failed to run Spring Boot application with debugging on port $DEBUG_PORT"
  # exit 1
# fi

echo "Spring Boot application started with JDWP debugging on port $DEBUG_PORT"
