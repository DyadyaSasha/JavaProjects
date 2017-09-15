package com.company;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06.05.17.
 */
public class XPathExample {
    public static void main(String[] args){
        final String expression1 = "//employee"; //хотим получить все элементы с именем employee используя рекурсивный спуск(//)
        final String expression01 ="content/emp/employee"; //хотим получить все элементы с именем employee не используя рекурсивный спуск(вывод такой же как и в expression0)
        final String expression2 ="content/emp/employee[@mgr=7698]"; //выведем все employee, у которых mgr=7698
        final String expression02 ="//employee[@mgr=7698]";//тот же вывод как и в expression2, но используется рекурсивный спуск
        final String expression3 ="//employee[../employee[ename='KING']/@empno=@mgr]"; //выводит сотрудников, которые находятся в подчинении у сотрудника с именем KING
        final String expression03 ="//employee[@mgr = ../employee[ename='KING']/@empno]";//то же самое, что и expression3
        final String expression4 = "//employee[not(@mgr)]";//выведем всех employee, у которых нет менеджера
        final String expression5 ="//employee[not(@empno<//employee/@empno)]";//выведет employee с максимальным empno
        final String expression05 ="//employee[not(@empno>//employee/@empno)]";//выведет employee с минимальным empno
        final String expression051="//employee[not(@empno>(preceding-sibling::employee | following-sibling::employee)/@empno)]";//выведет employee с минимальным empno(используются XPath оси)
        final String expression052="//employee[not(@empno<(preceding-sibling::employee | following-sibling::employee)/@empno)]";//выведет employee с максимальным empno(используются XPath оси)
        XPath xpath = XPathFactory.newInstance().newXPath();//получили объект Xpath
        XPathExpression xPathExpression = null;
        try {
//            xPathExpression = xpath.compile(expression1);//expression-выражение, содержащее синтаксис XPath
//            xPathExpression = xpath.compile(expression01);
//            xPathExpression = xpath.compile(expression2);
//            xPathExpression = xpath.compile(expression02);
//            xPathExpression = xpath.compile(expression3);
//            xPathExpression = xpath.compile(expression03);
//            xPathExpression = xpath.compile(expression4);
//            xPathExpression = xpath.compile(expression5);
//            xPathExpression = xpath.compile(expression05);
//            xPathExpression = xpath.compile(expression051);
            xPathExpression = xpath.compile(expression052);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = new FileInputStream("emp.xml");//файл находится в проекте, поэтому
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource(is);
        NodeList result =null;
        try {
            result = (NodeList)xPathExpression.evaluate(inputSource, XPathConstants.NODESET);//XPathConstants.NODESET - константа, которая показывает ожидаемый результат, который мы хотим получить
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        System.out.println(result.getLength());
        for (int i=0; i<result.getLength(); i++){
            System.out.println("node is: "+nodeToString(result.item(i)));
        }
    }

    private static String nodeToString(Node node){
        StringWriter sw= new StringWriter();//в StringWriter будет сериализоваться node
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");//убирает строчку <?xml version="1.0" encoding="UTF-8"?>
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (Exception e) {
            System.out.println("nodeToString exception");
        }
        return sw.toString();
    }
}
