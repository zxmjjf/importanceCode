package com.book2.B3_XML;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class B06_StAX_myselfFile {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        String fileName = "src/com/book2/B3_XML/MyXML_3.xml";

        InputStream inputStream = new FileInputStream(fileName);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

        System.out.println("解析文件：" + fileName + "\nEncoding: " + reader.getEncoding() + "\t\tversion" + reader.getVersion());
        while (reader.hasNext()) {
            int i = reader.next(); //reader.getEventType
            //处理元素，还可以处理元素对应的属性列表
            if (reader.isStartElement()) {
                System.out.println("开始解析元素：" + reader.getName() + "\t\t属性个数 = " + reader.getAttributeCount());
            }
            //处理文本
            if (reader.isCharacters()) {
                System.out.println("解析到文本"  + "\t" + reader.getText());
            }
            //处理空白部分，如果没有定义DTD或者XML schema
            if (reader.isWhiteSpace()) {
                System.out.println("你 怎 么 没 有 定 义 DTD 或者 XML schema");
            }
        }

        reader.close();
    }
}
