package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void process(Node parent, int level) {
        for (Node n = parent.getFirstChild();
             n != null;
             n = n.getNextSibling())
        {
            System.out.println(n.toString()+" NodeName:"+n.getNodeName()+" LocalName:"+n.getLocalName()+" Value:"+n.getNodeValue());
            process(n, level+1);
        }
    }
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc1 = null; //парсим входной файл
        try {
            doc1 = documentBuilder.parse(new File("/home/user/ForGitHub/JAVA/XMLProject/src/com/company/example.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Document doc = documentBuilder.parse(new FileInputStream(fileNme));//парсим входной поток

        //парсинг с валидацией XSD(W3C Schema)
//        Schema schema = null;
//        try {
//            schema = SchemaFactory
//                    .newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI)
//                    .newSchema();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//        Validator validator = schema.newValidator();
//        validator.validate(new DOMSource(doc));
        Element root = doc1.getDocumentElement(); //извлекаем корневой элемент
        process(root, 0);//рекурсивный проход по всем элементам xml файла
    }
}