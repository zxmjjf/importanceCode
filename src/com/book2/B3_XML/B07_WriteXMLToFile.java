package com.book2.B3_XML;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class B07_WriteXMLToFile {

    public static void main(String[] args) throws IOException, ParserConfigurationException {
        Path path = Paths.get("src/com/book2/B3_XML/MyXML_4");
        OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element element = document.createElement("jjf");
        document.appendChild(element);
        for (int i = 0; i < 3; ++i) {
            Element element1 = document.createElement("jjf" + i);
            for (int j = 0; j < 3; ++j) {
                Element element2 = document.createElement("child" + j);
                element1.appendChild(element2);
            }
            element.appendChild(element1);
        }

        DOMImplementation implementation = document.getImplementation();
        DOMImplementationLS implementationLS = (DOMImplementationLS) implementation.getFeature("LS", "3.0");
        LSSerializer serializer = implementationLS.createLSSerializer();
        serializer.getDomConfig().setParameter("format-pretty-print", true);

        LSOutput lsOutput = implementationLS.createLSOutput();
        lsOutput.setEncoding("UTF-8");
        lsOutput.setByteStream(outputStream);
        serializer.write(document, lsOutput);

        //outputStream.close();
    }

}
