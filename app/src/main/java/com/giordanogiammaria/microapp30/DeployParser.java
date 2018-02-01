package com.giordanogiammaria.microapp30;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Roberto on 15/01/2018.
 */

public class DeployParser {

    private Document document;

    public DeployParser(String filePath) {
        File file;
        if (filePath != null)
            file = new File(filePath);
        else
            file = createXMLFile();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();
    }

    public ArrayList<Component> getComponents() {
        NodeList componentNodes = document.getElementsByTagName("component");
        ArrayList<Component> components = new ArrayList<>();
        // crea componente dal tag component
        int compsLength = componentNodes.getLength();
        for (int i = 0; i < compsLength; i++) {
            Element compNode = (Element) componentNodes.item(i);
            String id = compNode.getAttribute("id");
            String type = compNode.getAttribute("type");
            Component component = new Component(id, ComponentType.valueOf(type));
            components.add(component);
            // aggiungi input e output alla componente
            if (compNode.hasChildNodes()) {
                NodeList childNodes = compNode.getChildNodes();
                int childLength = childNodes.getLength();
                for (int j = 0; j < childLength; j++) {
                    Element childNode = (Element) childNodes.item(j);
                    if (childNode.getTagName().equals("input")) {
                        String dataName = childNode.getAttribute("dataname");
                        String sendId = childNode.getAttribute("id");
                        component.addInputSender(sendId, dataName);
                    } else if (childNode.getTagName().equals("output")) {
                        String destId = childNode.getAttribute("id");
                        component.addOutputReceiver(destId);
                    }
                }
            }
        }
        printComponents(components);
        return components;
    }

    private static File createXMLFile() {
        File file = new File("D:\\Documenti\\Università\\Materiale magistrale\\Ingegneria, Gestione ed Evoluzione del Software\\file2.xml");
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("xml");
            doc.appendChild(rootElement);

            Element comp1 = doc.createElement("component");
            rootElement.appendChild(comp1);
            comp1.setAttribute("id", "1");
            comp1.setAttribute("type", "BLANK");

            Element output1 = doc.createElement("output");
            output1.setAttribute("id", "2");
            comp1.appendChild(output1);


            Element comp2 = doc.createElement("component");
            rootElement.appendChild(comp2);
            comp2.setAttribute("id", "2");
            comp2.setAttribute("type", "BLANK");

            Element input2 = doc.createElement("input");
            input2.setAttribute("id", "1");
            input2.setAttribute("dataname", "STRING");
            comp2.appendChild(input2);

            Element output21 = doc.createElement("output");
            output21.setAttribute("id", "3");
            comp2.appendChild(output21);

            Element output22 = doc.createElement("output");
            output22.setAttribute("id", "4");
            comp2.appendChild(output22);


            Element comp3 = doc.createElement("component");
            comp3.setAttribute("id", "3");
            comp3.setAttribute("type", "BLANK");
            rootElement.appendChild(comp3);

            Element input3 = doc.createElement("input");
            input3.setAttribute("id", "2");
            input3.setAttribute("dataname", "STRING");
            comp3.appendChild(input3);


            Element comp4 = doc.createElement("component");
            comp4.setAttribute("id", "4");
            comp4.setAttribute("type", "BLANK");
            rootElement.appendChild(comp4);

            Element input4 = doc.createElement("input");
            input4.setAttribute("id", "2");
            input4.setAttribute("dataname", "STRING");
            comp4.appendChild(input4);

            Element output4 = doc.createElement("output");
            output4.setAttribute("id", "5");
            comp4.appendChild(output4);


            Element comp5 = doc.createElement("component");
            comp5.setAttribute("id", "5");
            comp5.setAttribute("type", "BLANK");
            rootElement.appendChild(comp5);

            Element input5 = doc.createElement("input");
            input5.setAttribute("id", "4");
            input5.setAttribute("dataname", "STRING");
            comp5.appendChild(input5);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }

        return file;
    }

    private static void printComponents(ArrayList<Component> components) {
        System.out.println("Componenti:");
        for (Component comp : components) {
            System.out.println(comp.toString());
            for (String sendId : comp.getInputSenders().keySet())
                System.out.println("- input: " + sendId);
            for (String destId : comp.getOutputReceivers())
                System.out.println("- output: " + destId);
        }
    }

}
