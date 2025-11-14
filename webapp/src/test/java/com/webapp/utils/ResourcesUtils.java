package com.webapp.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;
import org.springframework.util.ResourceUtils;

public class ResourcesUtils {

  /**
   * Loads a file from the classpath and returns its content as a String. Works both in IDE and
   * packaged JAR environments.
   *
   * @param fileName the file name relative to the resources folder
   * @return file content as String
   * @throws IOException if the file cannot be found or read
   */
  public static String loadResourceFileAsString(String fileName)
      throws IOException, URISyntaxException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null) {
      classLoader = ResourcesUtils.class.getClassLoader();
    }

    var resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new IOException("Resource not found: " + fileName);
    }

    try {
      // Works if the resource is available as a normal file
      Path path = Paths.get(resource.toURI());
      return Files.readString(path, StandardCharsets.UTF_8);
    } catch (FileSystemNotFoundException e) {
      // Fallback: handle resource inside a JAR
      try (FileSystem fs = FileSystems.newFileSystem(resource.toURI(), Collections.emptyMap());
          InputStream inputStream = fs.provider().newInputStream(fs.getPath(fileName))) {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      }
    } catch (Exception e) {
      // Last resort fallback
      try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
        if (inputStream == null) {
          throw new IOException("Resource not found: " + fileName);
        }
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      }
    }
  }

  /**
   * Loads any resource file from the classpath and returns its content as a String. Works for files
   * like JSON, TXT, XML, etc.
   *
   * @param resourceName the file path relative to classpath (e.g., "register_payload.json")
   * @return content of the file as String
   * @throws IOException if the file is not found or cannot be read
   */
  public static String loadResourceAsString(String resourceName) throws IOException {
    File file = ResourceUtils.getFile("classpath:" + resourceName);
    Path path = file.toPath();
    return Files.readString(path, StandardCharsets.UTF_8);
  }

  public static Path loadResourceAsPath(String resourceName) throws IOException {
    File file = ResourceUtils.getFile("classpath:" + resourceName);
    return file.toPath();
  }

  public static String loadResourceAsString1(String resourceName) throws IOException {
    return Files.readString(loadResourceAsPath(resourceName));
  }
}
