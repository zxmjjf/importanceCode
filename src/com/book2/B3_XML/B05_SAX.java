package com.book2.B3_XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class B05_SAX {
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        String url = "https://www.csdn.net";

        DefaultHandler handler = new DefaultHandler(){
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (localName.equals("a") && attributes != null) {
                    for (int i = 0; i < attributes.getLength(); ++i) {
                        String aName = attributes.getLocalName(i);
                        if (aName.equals("href")) {
                            System.out.println(attributes.getValue(i));
                        }
                    }
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
        factory.setValidating(true);

        SAXParser saxParser = factory.newSAXParser();
        InputStream inputStream = new URL(url).openStream();
        saxParser.parse(inputStream, handler);
    }

}
