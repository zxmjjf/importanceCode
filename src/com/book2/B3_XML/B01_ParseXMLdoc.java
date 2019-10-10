package com.book2.B3_XML;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class B01_ParseXMLdoc {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //初始配置操作
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/com/book2/B3_XML/MyXML_2.xml");
        Element element = document.getDocumentElement();

        //操作根节点
        System.out.println(element);
        System.out.println("rootName: " + element.getTagName()); //打印根名字
        System.out.print("version: " + element.getAttribute("version")); //获得指定属性的值
        element.setAttribute("jjf", "姜剑锋");  //添加属性,但是对应的文件不会更新。
        System.out.println("\t\tjjf:" + element.getAttribute("jjf"));

        //获得特定的子孙节点
        System.out.println("子标签节点的名字");
        NodeList nodeListName = element.getElementsByTagName("number");
        int length = nodeListName.getLength();
        System.out.println(length + "\t");
        for (int i = 0; i < length; ++i) {
            Node node = nodeListName.item(i).getFirstChild();
            if (node instanceof Text) {
                String string = ((Text)node).getData().trim();
                System.out.print(nodeListName.item(i).getNodeName() + "\t" + nodeListName.item(i).getNodeValue() + ": " + string + "\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("操作子节点.....");
        NodeList nodeList = element.getChildNodes();
        length = nodeList.getLength();
        System.out.print(length + "：\t");
        Node node;
        for (int i = 0; i < length; ++i) {
            //获得所有子节点，不是子孙节点
            node = nodeList.item(i);
            //System.out.println(node);
            System.out.println(node.getTextContent().trim());
            if (node instanceof Element && node.hasAttributes()) {
                //操作节点的属性
                NamedNodeMap map = node.getAttributes();
                System.out.println(((Element) node).getTagName() + map.item(0).getNodeName() + ": " + map.item(0).getNodeValue());
            }


        }

    }
}
