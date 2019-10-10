package com.book2.B3_XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLOutput;

public class B02_Node {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/com/book2/B3_XML/MyXML_3.xml");
        Element element = document.getDocumentElement();


        System.out.println(element.getTagName() + ": " + element.getNodeValue() + "type = " + element.getNodeType());
        System.out.println(element.getChildNodes().getLength() + element.getBaseURI());
        System.out.println(element.getNamespaceURI());
        Node node = element.getElementsByTagName("information").item(1);

        System.out.println(node.getAttributes().item(0).getNodeValue()); //兴趣爱好
        System.out.println(node.getChildNodes().item(1).getNodeValue() + node.getChildNodes().item(1).getOwnerDocument().getDoctype());
        System.out.println(node.getChildNodes().item(2).getNodeValue());



    }
}
