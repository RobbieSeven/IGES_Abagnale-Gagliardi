package com.giordanogiammaria.microapp30.Subsystem;

import com.giordanogiammaria.microapp30.Component;

import org.jgrapht.DirectedGraph;

import java.util.ArrayList;

/**
 * Created by Roberto on 22/01/2018.
 */

public class DepthFirstOrder<V> {

    private DirectedGraph<V, DirEdge> graph;
    private ArrayList<V> sortedVertices;
    private ArrayList<Node<V>> nodes;
    private boolean notDAG;

    public DepthFirstOrder(DirectedGraph<V, DirEdge> graph) {
        this.graph = graph;
        sortedVertices = new ArrayList<>();
        nodes = new ArrayList<>();
        notDAG = false;
    }

    public ArrayList<V> sort() {
        for (V v : graph.vertexSet())
            nodes.add(new Node<>(v, NodeColor.WHITE));
        for (Node<V> n : nodes)
            if (n.color == NodeColor.WHITE)
                visit(n);
        if (notDAG)
            return null;
        return sortedVertices;
    }

    private void visit(Node<V> node) {
        if (node.color == NodeColor.GREY)
            notDAG = true;
        else if (node.color == NodeColor.WHITE) {
            node.color = NodeColor.GREY;
            ArrayList<V> neighbors = new ArrayList<>();
            for (DirEdge e : graph.outgoingEdgesOf(node.vertex))
                neighbors.add((V) e.getTarget());
            for (Node n : nodes)
                if (neighbors.contains(n.vertex))
                    visit(n);
            node.color = NodeColor.BLACK;
            sortedVertices.add(0, node.vertex);
        }
    }

    private enum NodeColor {
        WHITE, GREY, BLACK
    }

    private class Node<E> {

        private E vertex;
        private NodeColor color;

        Node(E vertex, NodeColor color) {
            this.vertex = vertex;
            this.color = color;
        }

    }

}
