package com.web.xml;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class StudentTest {

    @Test
    public void testReadXML() throws IOException {
        System.out.println("Hello, this is a test for reading XML in StudentTest.");

        InputStream inputStream = StudentTest.class
                .getClassLoader()
                .getResourceAsStream("students.xml");

        String xml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        System.out.println(xml);
    }

    @Test
    public void testReadXML_Java11() throws IOException {
        try (InputStream is = StudentTest.class
                .getClassLoader()
                .getResourceAsStream("students.xml")) {

            String xml = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(xml);
        }
    }

    @Test
    public void testReadXML_Junit() throws IOException {

        String xml = Files.readString(Path.of("src/test/resources/students.xml"));
        System.out.println(xml);

    }
}
