package com.book2.B3_XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class B05_SAX_myselfFile {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String fileName = "E:\\newIDEA\\importanceCode\\src\\com\\book2\\B3_XML\\MyXML_3.xml";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        DefaultHandler handler = new DefaultHandler() {
            //处理文档开头
            @Override
            public void startDocument() throws SAXException {
                System.out.println("开始解析： " + fileName);
            }

            //处理元素：元素名，属性
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                //System.out.println(localName);
                System.out.println("解析元素：" + localName + "\t\t属性： " + attributes.getLength() + " 个\t");
                if (attributes.getLength() != 0) {
                    for (int i = 0; i < attributes.getLength(); ++i) {
                        System.out.println(attributes.getLocalName(i) + "=" + attributes.getValue(i));
                    }
                }
            }

            //处理文本子节
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                //System.out.println("start = " + start + "\tlength = " + length);
                char[] chars = Arrays.copyOfRange(ch, start, start + length);
                String string = new String(chars);
                //System.out.println(chars);
                if (string.equals("姜剑锋")) {
                    System.out.println(chars);
                }
            }
        };


        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        SAXParser parser = factory.newSAXParser();
        parser.parse(fileInputStream, handler);
    }
}
