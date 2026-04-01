package com.web.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.File;

public class ReadXMLExample {
    public static void main(String[] args) {
        try {
            File file = new File("students.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String age = element.getElementsByTagName("age").item(0).getTextContent();

                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("----------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
