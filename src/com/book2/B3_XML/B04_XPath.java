package com.book2.B3_XML;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class B04_XPath {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        //获得DOM文档对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/com/book2/B3_XML/MyXML_3.xml");
        //获得XPath对象g
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        //用法一：直接定位到元素终点。子节点只有文本节点
        System.out.println(xPath.evaluate("/jjf/baseInformation/name", document));
        //用法二：定位到中间节点，子节点有元素节点。但没有同名的兄弟节点
        NodeList nodeList1 = (NodeList) xPath.evaluate("/jjf/baseInformation/name", document, XPathConstants.NODESET);
        System.out.println(nodeList1.getLength());
        //System.out.println(nodeList1.item(0).getNodeName());
        //用法三：定位到中间节点，
        Node node1 = (Node) xPath.evaluate("jjf/baseInformation", document, XPathConstants.NODE);
        NodeList nodeList2 = node1.getChildNodes();
        System.out.println(nodeList2.getLength());
        System.out.println(node1.getTextContent());
        System.out.println(node1.getFirstChild().getTextContent());
        //用法四：定位到中间节点，但不转化为节点
        System.out.println(xPath.evaluate("jjf/baseInformation", document));//输出所有不能被解析的字符， CDATA数据

        //其他用法：
        NodeList nodeList3 = (NodeList) xPath.evaluate("baseInformation", document, XPathConstants.NODESET);
        System.out.println(nodeList3.getLength());

        //用求得的节点作为基本节点
        Node node2 = (Node) xPath.evaluate("jjf/node()", document, XPathConstants.NODE);
        NodeList nodeList4 = (NodeList) xPath.evaluate("baseInformation[1]", node2, XPathConstants.NODESET);
        System.out.println(nodeList4.getLength());
        String string =  xPath.evaluate("baseInformation[1]", node2);
        System.out.println(string);

        NodeList nodeList5 = (NodeList) xPath.evaluate("jjf/node()", document, XPathConstants.NODESET);
        System.out.println(nodeList5.getLength());
        for (int i = 0; i < nodeList5.getLength(); ++i) {
            Node node = nodeList5.item(i);
            System.out.println(node.getNodeName());
            NodeList nodeList = (NodeList) xPath.evaluate("node()", node, XPathConstants.NODESET);
            for (int j = 0; j < nodeList.getLength(); ++j) {
                System.out.println("\t" + nodeList.item(j).getNodeName() + "-->" + nodeList.item(j).getTextContent());
            }
        }

        String string3 = xPath.evaluate("jjf/baseInformation[name=\"姜剑锋\"]", document);
        System.out.println(string3);

        Node node = (Node) xPath.evaluate("jjf/baseInformation/birth", document, XPathConstants.NODE);
        System.out.println(node.getNamespaceURI());




    }


}
