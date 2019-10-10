package com.book2.B3_XML;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static javax.xml.stream.XMLStreamConstants.*;

public class B07_UseStAX_WriteXML {
    //设置END_ELEMENT之前的异常事件是否否END_ELEMENT
    private static final boolean START = true;
    private static final boolean END = false;

    public static void main(String[] args) throws IOException, XMLStreamException, SAXException, ParserConfigurationException, XPathExpressionException {
        //搭建XML输入源
        Path inputFile = Paths.get("src/com/book2/B3_XML/MyXML_5.xml");
        InputStream inputStream = Files.newInputStream(inputFile);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream); //还有很多构造方式
        //搭建XML输出源
        Path outputFile = Paths.get("src/com/book2/B3_XML/MyXML_6.xml");
        OutputStream outputStream = Files.newOutputStream(outputFile, StandardOpenOption.CREATE);
        XMLOutputFactory factory1 = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory1.createXMLStreamWriter(outputStream);

        //使用结构定位器XPath， 为了高校的判断某个元素是否具有子元素，以便控制写出格式
        DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
        factory2.setValidating(true); //开启DTD验证
        factory2.setIgnoringElementContentWhitespace(true); //忽略纯空白节点
        factory2.setNamespaceAware(true); //开启名空间
        DocumentBuilder builder = factory2.newDocumentBuilder(); //获得指定配置的文档流
        Document document = builder.parse("src/com/book2/B3_XML/MyXML_5.xml"); //获得指定文件的文档对象
        //获得XPath对象g
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath(); //新建普通的XPath对象

        String format = "\n"; //控制元素写出时的缩进，新的元素首先先换行
        boolean endElementSign = true; //是否连续两次发生了END_ELEMENT事件

        //在读出的时候顺便写出XML
        //开始处理文档头
        if (reader.getEventType() == START_DOCUMENT) {
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeCharacters("\n");
        }
        int next; //下一次事件的事件类型
        while (reader.hasNext()) {
            next = reader.next(); //

            //写入DTD数据
            if (next == DTD) {
                writer.writeDTD(reader.getText());
            }

            //元素开头
            if (next == START_ELEMENT) {
                endElementSign = START;
                //控制写出格式
                writer.writeCharacters(format); //打印格式
                //修改下一个元素的开头格式
                String elementName = reader.getName().toString();
                NodeList nodeList = (NodeList) xPath.evaluate("//" + elementName + "/*", document, XPathConstants.NODESET);
                if (nodeList.getLength() > 1) {
                    format = format + "\t"; //如果有子元素，则后续多一个制表符 "\t"
                }

                //写出
                writer.writeStartElement(elementName);

                //写出属性
                int attLength = reader.getAttributeCount();
                for (int i = 0; i < attLength; ++i) {
                    String name = reader.getAttributeName(i).toString();
                    String value = (String) reader.getAttributeValue(i);
                    writer.writeAttribute(name, value);
                }
            }

            //文本节点
            if (next == CHARACTERS) {
                writer.writeCharacters(reader.getText());
            }

            //元素结尾，控制相邻结尾的子元素
            if (next == END_ELEMENT) {
                String elementName = reader.getName().toString();
                //修改格式
                NodeList nodeList = (NodeList) xPath.evaluate("//" + elementName + "/*", document, XPathConstants.NODESET);
                if (nodeList.getLength() > 1) {
                    format = format.substring(0, format.length() - 1);
                }
                //如果连续两个结尾元素，则控制格式，因为之前只有在元素开头才会控制格式
                if (endElementSign == END) {
                    writer.writeCharacters(format);
                }

                writer.writeEndElement();
                endElementSign = END; //标记此时已经检查元素结尾

            }

            //文档结尾
            if (next == END_DOCUMENT) {
                writer.writeEndDocument();
            }

        }

        //关闭流，释放资源
        reader.close();
        writer.close();
    }
}
