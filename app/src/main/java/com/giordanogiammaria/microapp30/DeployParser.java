package com.giordanogiammaria.microapp30;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Roberto on 15/01/2018.
 */

public class DeployParser {

    private Document document;

    public DeployParser(String filePath) {
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        // document.getDocumentElement().normalize();
    }

    public ArrayList<Component> getComponents() {
        NodeList componentNodes = document.getElementsByTagName("component");
        ArrayList<Component> components = new ArrayList<>();
        int compsLength = componentNodes.getLength();
        for (int i = 0; i < compsLength; i++) {
            Element compNode = (Element) componentNodes.item(i);
            String id = compNode.getAttribute("id");
            String type = compNode.getAttribute("type");
            Component component = new Component(id, ComponentType.valueOf(type));
            components.add(component);
            NodeList inputNodes = document.getElementsByTagName("input");
            int inputLength = inputNodes.getLength();
            for (int j = 0; j < inputLength; j++) {
                Element inputNode = (Element) inputNodes.item(j);
                String dataName = inputNode.getAttribute("dataname");
                String sendId = inputNode.getAttribute("id");
                component.addInputSender(sendId, dataName);
            }
            NodeList outputNodes = document.getElementsByTagName("output");
            int outputLength = outputNodes.getLength();
            for (int k = 0; k < outputLength; k++) {
                Element outputNode = (Element) outputNodes.item(k);
                String destId = outputNode.getAttribute("id");
                component.addOutputReceiver(destId);
            }
        }
        return components;
    }

}
