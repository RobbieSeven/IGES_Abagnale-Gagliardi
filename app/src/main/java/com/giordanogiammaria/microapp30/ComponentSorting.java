package com.giordanogiammaria.microapp30;

import com.giordanogiammaria.microapp30.Subsystem.DepthFirstOrder;
import com.giordanogiammaria.microapp30.Subsystem.DirEdge;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 22/01/2018.
 */

public class ComponentSorting {

    public static ArrayList<Component> sortComponents(ArrayList<Component> components) {
        HashMap<String, Component> componentsMap = new HashMap<>();
        for (Component comp : components)
            componentsMap.put(comp.getId(), comp);
        DirectedGraph<Component, DirEdge> graph = createGraph(componentsMap);
        printGraph(graph);
        DepthFirstOrder<Component> dfs = new DepthFirstOrder<>(graph);
        return dfs.sort();
    }

    private static DirectedGraph<Component, DirEdge> createGraph(HashMap<String, Component> components) {
        DirectedGraph<Component, DirEdge> graph = new DefaultDirectedGraph<>(DirEdge.class);
        for (Component comp : components.values())
            graph.addVertex(comp);
        for (Component sourceComp : components.values())
            for (String targetId : sourceComp.getOutputReceivers())
                graph.addEdge(sourceComp, components.get(targetId));
        return graph;
    }

    private static void printGraph(DirectedGraph<Component, DirEdge> graph) {
        System.out.println("Vertici del grafo:");
        for (Component v : graph.vertexSet())
            System.out.print(v.toString() + " - ");
        System.out.println();
        System.out.println("Archi del grafo:");
        for (DirEdge e : graph.edgeSet())
            System.out.println(e.toString() + " - ");
    }

}
