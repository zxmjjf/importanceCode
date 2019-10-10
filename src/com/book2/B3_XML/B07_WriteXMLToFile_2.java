package com.book2.B3_XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class B07_WriteXMLToFile_2 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Path path = Paths.get("src/com/book2/B3_XML/MyXML_7");
        OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        //输出Document
        Document document1 = builder.newDocument();
        Element element = document1.createElement("jjf");
        document1.appendChild(element);
        //输入Document
        Document document2 = builder.parse("src/com/book2/B3_XML/MyXML_3.xml");

        //获得XPath对象g
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

    }
}
