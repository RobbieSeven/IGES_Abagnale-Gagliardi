package com.giordanogiammaria.microapp30.sorting;

import org.jgrapht.DirectedGraph;

import java.util.ArrayList;


class DepthFirstOrder<V> {

    private DirectedGraph<V, DirEdge> graph;
    private ArrayList<V> sortedVertices;
    private ArrayList<DFSNode<V>> nodes;
    private boolean notDAG;

    DepthFirstOrder(DirectedGraph<V, DirEdge> graph) {
        this.graph = graph;
        sortedVertices = new ArrayList<>();
        nodes = new ArrayList<>();
        notDAG = false;
    }

    ArrayList<V> sort() {
        for (V v : graph.vertexSet())
            nodes.add(new DFSNode<>(v, DFSNodeColor.WHITE));
        for (DFSNode<V> n : nodes)
            if (n.color == DFSNodeColor.WHITE)
                visit(n);
        if (notDAG)
            return null;
        return sortedVertices;
    }

    private void visit(DFSNode<V> node) {
        if (node.color == DFSNodeColor.GREY)
            notDAG = true;
        else if (node.color == DFSNodeColor.WHITE) {
            node.color = DFSNodeColor.GREY;
            ArrayList<V> neighbors = new ArrayList<>();
            for (DirEdge e : graph.outgoingEdgesOf(node.vertex))
                neighbors.add((V) e.getTarget());
            for (DFSNode n : nodes)
                if (neighbors.contains(n.vertex))
                    visit(n);
            node.color = DFSNodeColor.BLACK;
            sortedVertices.add(0, node.vertex);
        }
    }

    private enum DFSNodeColor {
        WHITE, GREY, BLACK
    }

    private class DFSNode<E> {

        private E vertex;
        private DFSNodeColor color;

        DFSNode(E vertex, DFSNodeColor color) {
            this.vertex = vertex;
            this.color = color;
        }

    }

}
