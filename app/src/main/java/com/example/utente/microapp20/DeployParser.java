package com.example.utente.microapp20;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.utente.microapp20.exceptions.InvalidComponentException;
import com.example.utente.microapp20.exceptions.NoMoreSpaceException;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DeployParser {

    private DocumentBuilder db;
    private Document doc;
    private ComponentCreator creator;
    private MAComponent startComp = null;
    private DirectedGraph<MAComponent, DirEdge> grafo;
    private Map<String, MAComponent> idComponents;
    private String icon;
    private String desc;
    private Application app;

    public DeployParser(ComponentCreator crea, MicroAppGenerator a) throws ParserConfigurationException {

        DocumentBuilderFactory bf = DocumentBuilderFactory.newInstance();
        bf.setNamespaceAware(true);
        db = bf.newDocumentBuilder();
        creator = crea;
        app = a;
    }

    public MAComponent getStartComp() {
        return startComp;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return desc;
    }

    public void setDeployFile(File f) throws SAXException, IOException {
        Log.d("path:",f.getAbsolutePath()+"questo è il path");
        doc = db.parse(f);
        try {
            icon = doc.getElementsByTagName("icon").item(0).getTextContent();
        } catch (Exception e) {
            icon = "app_icon";
        }
        Log.d("icon", "icon o " +icon);
        try {
            desc = doc.getElementsByTagName("description").item(0).getTextContent();
        } catch (Exception e) {
            desc = "";
        }
    }

    public void getAppStructure() {

    }

    public DirectedGraph<MAComponent, DirEdge> createGraph() throws InvalidComponentException, NoMoreSpaceException {
        grafo = new DefaultDirectedGraph<MAComponent, DirEdge>(DirEdge.class);

        NodeList components = doc.getElementsByTagName("component");
        int l = components.getLength();
        idComponents = new HashMap<>(l);
        for (int i = 0; i < l; i++) {
            Element n = (Element) components.item(i);
            String idComp = n.getAttribute("id");
            String type = n.getAttribute("type");
            MAComponent newComp = null;

            String description = "";
            NodeList nDesc = n.getElementsByTagName("description");
            if (nDesc.getLength() > 0) {
                Element desc = (Element) nDesc.item(0);
                description = desc.getTextContent();
            }
            Log.d("INFO", "sono qui 127 " + idComp + " "+ type);
            creator = new ConcreteComponentCreator();
            newComp = creator.createComponentService(type, idComp, description);

            grafo.addVertex(newComp);
            if (i == 0)
                startComp = newComp;
            idComponents.put(idComp, newComp);
        }


        for (int i = 0; i < l; i++) {

            Element n = (Element) components.item(i);
            NodeList outputs = n.getElementsByTagName("output");
            int k = outputs.getLength();

            for (int j = 0; j < k; j++) {
                Element out = (Element) outputs.item(j);

                if (!out.getAttribute("id").isEmpty()) {
                    MAComponent scomp = idComponents.get(out.getAttribute("id"));
                    MAComponent fcomp = idComponents.get(n.getAttribute("id"));
                    grafo.addEdge(fcomp, scomp);
                    fcomp.addOutput(DataType.valueOf(out.getAttribute("datatype")), scomp.getId(),
                            out.getAttribute("binding"));
                    scomp.addInput(DataType.valueOf(out.getAttribute("datatype")), fcomp.getId(),
                            out.getAttribute("binding"));
                }
            }
        }
        return grafo;
    }


    public String createGraphCode() throws InvalidComponentException, NoMoreSpaceException {
        String grafoCode = "";

        grafoCode +="MAComponent newComp = null;\n";
        grafoCode +="MAComponent startComp = null;\n";

        grafoCode += "DefaultDirectedGraph<MAComponent, DirEdge> grafo = new DefaultDirectedGraph<MAComponent, DirEdge>(DirEdge.class);\n";

        NodeList components = doc.getElementsByTagName("component");
        int l = components.getLength();

        grafoCode += "HashMap<String, MAComponent> idComponents = new HashMap<String, MAComponent>(" + l + ");\n";

        grafoCode += "ConcreteComponentCreator creator = new ConcreteComponentCreator();\n";
        grafoCode += "try {\n";
        for (int i = 0; i < l; i++) {
            Element n = (Element) components.item(i);
            String idComp = n.getAttribute("id");
            String type = n.getAttribute("type");
            MAComponent newComp = null;

            String description = "";
            NodeList nDesc = n.getElementsByTagName("description");
            if (nDesc.getLength() > 0) {
                Element desc = (Element) nDesc.item(0);
                description = desc.getTextContent();
            }

            grafoCode += "newComp = creator.createComponent(\"" + type + "\", \"" + idComp + "\", \"" + description + "\");\n";

            String condition = n.getAttribute("condition");
            grafoCode += "newComp.setCondition(\"" + condition + "\");\n";

            grafoCode += "grafo.addVertex(newComp);\n";
            if (i == 0)
                grafoCode += "startComp = newComp;\n";

            grafoCode += "idComponents.put(\"" + idComp + "\", newComp);\n";
        }
        grafoCode += "MAComponent scomp = null;\n";
        grafoCode += "MAComponent fcomp = null;\n";


        for (int i = 0; i < l; i++) {

            Element n = (Element) components.item(i);
            NodeList outputs = n.getElementsByTagName("output");
            int k = outputs.getLength();

            for (int j = 0; j < k; j++) {
                Element out = (Element) outputs.item(j);

                if (!out.getAttribute("id").isEmpty()) {

                    grafoCode += "scomp = idComponents.get(\"" + out.getAttribute("id") + "\");\n";
                    grafoCode += "fcomp = idComponents.get(\"" + n.getAttribute("id") + "\");\n";

                    grafoCode += "grafo.addEdge(fcomp, scomp);\n";

                    grafoCode += "fcomp.addOutput(DataType.valueOf(\"" + out.getAttribute("datatype") + "\"), scomp.getId(), \""+ out.getAttribute("binding") + "\");\n";
                    grafoCode += "scomp.addInput(DataType.valueOf(\"" + out.getAttribute("datatype") + "\"), fcomp.getId(), \"" + out.getAttribute("binding") + "\");\n";
                }
            }
        }
        grafoCode += "}catch(Exception e){}\n";

        return grafoCode;
    }

}

/*
 * ABBOZZO DELL'ALGORTIMO
 * 
 * NodeList components=document.getElementsByTagName("component"); creazione del
 * grafo dalla lista di component visita DfS con controllo per ogni nodo
 */
