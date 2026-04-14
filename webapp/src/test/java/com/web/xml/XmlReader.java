package com.web.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XmlReader {
    public static String getXmlByKey(String key) throws Exception {

        InputStream is = XmlReader.class
                .getClassLoader()
                .getResourceAsStream("data.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);

        Node node = doc.getElementsByTagName(key).item(0);

        String xml = getXmlBlock(doc, node);
       // String xmlString = xml.replace("\\n", "")
        //        .replace("\\r", "");

        String xmlString = xml
                .replaceFirst("<\\?xml.*?\\?>", "")   // remove xml header
                .replaceAll(">\\s+<", "><")           // remove whitespace between tags
                .trim();

        return xmlString;
    }

    public static void main(String[] args) throws Exception {

        String student = getXmlByKey("student");
        System.out.println(student);

        String employee = getXmlByKey("employee");
        System.out.println(employee);
    }

    public static String getXmlBlock(Document doc, Node node) throws Exception {

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(node), new StreamResult(writer));

        return writer.toString();
    }
}
