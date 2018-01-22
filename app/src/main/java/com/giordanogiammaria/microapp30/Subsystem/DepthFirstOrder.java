package com.giordanogiammaria.microapp30.Subsystem;

import com.giordanogiammaria.microapp30.Component;
import com.giordanogiammaria.microapp30.ComponentSorting;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

import java.util.ArrayList;

/**
 * Created by Roberto on 22/01/2018.
 */

public class DepthFirstOrder {

    DirectedGraph<Component, DirEdge> graph;
    ArrayList<Component> sortedComponents;
    ArrayList<Node<Component>> nodes;
    boolean notDAG;

    public DepthFirstOrder(DirectedGraph<Component, DirEdge> graph) {
        this.graph = graph;
        sortedComponents = new ArrayList<>();
        nodes = new ArrayList<>();
        notDAG = false;
    }

    public ArrayList<Component> sort() {
        for (Component comp : graph.vertexSet())
            nodes.add(new Node<>(comp, NodeColor.WHITE));
        for (Node<Component> n : nodes)
            if (n.getColor() == NodeColor.WHITE)
                visit(n);
        return sortedComponents;
    }

    private void visit(Node<Component> node) {
        if (node.getColor() == NodeColor.GREY) {
            notDAG = true;
            return;
        } else if (node.getColor() == NodeColor.WHITE) {
            node.setColor(NodeColor.GREY);
            ArrayList<Component> neighbors = new ArrayList<>();
            for (DirEdge e : graph.outgoingEdgesOf(node.getVertex())) {
                neighbors.add((Component) e.getTarget());
            }
            for (Node n : nodes) {
                if (n.getVertex())
            }
        }
    }

    private enum NodeColor {
        WHITE, GREY, BLACK;
    }

    private class Node<V> {

        private V vertex;
        private NodeColor color;

        public Node(V vertex, NodeColor color) {
            this.vertex = vertex;
            this.color = color;
        }

        public V getVertex() {
            return vertex;
        }

        public NodeColor getColor() {
            return color;
        }

        public void setColor(NodeColor color) {
            this.color = color;
        }

    }

}
