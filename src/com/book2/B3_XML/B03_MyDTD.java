package com.book2.B3_XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class B03_MyDTD {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/com/book2/B3_XML/MyXML_3.xml");

        Element element = document.getDocumentElement();
        NodeList rootNodeList = element.getChildNodes();
        System.out.println(rootNodeList.getLength());

        //factory.setValidating(true);
        System.out.println(factory.isValidating());
    }
}
