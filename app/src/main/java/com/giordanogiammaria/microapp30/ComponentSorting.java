package com.giordanogiammaria.microapp30;

import com.giordanogiammaria.microapp30.Subsystem.DirEdge;

import org.jgraph.graph.Edge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Roberto on 22/01/2018.
 */

public class ComponentSorting {

    public static ArrayList<Component> sortComponents(ArrayList<Component> components, Component startComp) {
        HashMap<String, Component> componentsMap = new HashMap<>();
        for (Component comp : components)
            componentsMap.put(comp.getId(), comp);
        DirectedGraph<Component, DirEdge> graph = createGraph(componentsMap);
        DepthFirstOrder<Component, DirEdge> dfs = new DepthFirstOrder<>(graph, startComp);
        return dfs.reversePost();
    }

    private static DirectedGraph<Component, DirEdge> createGraph(HashMap<String, Component> components) {
        DirectedGraph<Component, DirEdge> graph = new DefaultDirectedGraph<>(DirEdge.class);
        for (Component comp : components.values())
            graph.addVertex(comp);
        for (Component sourceComp : components.values())
            for (String targetId : sourceComp.getOutputReceivers()) {
                Component targetComp = components.get(targetId);
                graph.addEdge(sourceComp, targetComp);
            }
        return graph;
    }

    private ArrayList<Component> sort(DirectedGraph<Component, DirEdge> graph) {
        ArrayList<Component> sortedComponents = new ArrayList<>();
        ArrayList<Node<Component>> nodes = new ArrayList<>();
        for (Component comp : graph.vertexSet())
            nodes.add(new Node<>(comp, NodeColor.WHITE));
        boolean notDAG = false;

        for (Node<Component> n : nodes)
            if (n.getColor() == NodeColor.WHITE)
                visit(n);
        return sortedComponents;
    }

    void visit(Node<Component> node) {
        if (node.getColor() == NodeColor.GREY) {
            notDag = true;
            return;
        } else if (node.getColor() == NodeColor.WHITE) {
            node.setColor(NodeColor.GREY);

        }
    }

    private enum NodeColor {
        WHITE, GREY, BLACK;
    }

    private static class Node<V> {

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

    /*public static ArrayList<Component> depthFirstOrder(DirectedGraph<Component, DirEdge> graph) {
        visited=new HashSet<V>();
        dfs (grafo, v);
        Set<V> set=grafo.vertexSet();
        int size=set.size();
        Object [] nodes= new Object[size];

        set.toArray(nodes);
        for (int i=0; i<size;i++)
            if (!visited.contains(nodes[i])){
                dfs(grafo, (V) nodes[i]);
                //	firstComp.add((V) nodes[i]);
            }
    }

    @SuppressWarnings("unchecked")
    private void dfs( DirectedGraph<V,  DirEdge>  grafo, V v)
    {	if (grafo.inDegreeOf(v)>0) return;
        visited.add(v);
        reversePost.add (v);
        Set<DirEdge> set=grafo.outgoingEdgesOf(v);
        int size=set.size();
        DirEdge[] edges = new DirEdge[size];
        grafo.outgoingEdgesOf(v).toArray(edges);
        for (int i=0; i<size;i++){
            V node= (V) edges[i].getTarget();
            if (!visited.contains(node))
                grafo.removeEdge(edges[i]);
            dfs(grafo, node);}
        //grafo.removeVertex(v);//aggiunto ora
    }*/

/*
    // The function to do Topological Sort. It uses
    // recursive topologicalSortUtil()
    private static void topologicalSort(Graph graph) {

        int V = graph.vertexSet().size();   // No. of vertices
        LinkedList<Component> adj[]; // Adjacency List
        int v = V;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
        for (Object e : graph.edgeSet()) {
            DirEdge edge = (DirEdge) e;
            adj[(Component) edge.getSource()].add(edge.getTarget());
        }

        Stack stack = new Stack();
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, adj, visited, stack);
        // Print contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }

    private static void topologicalSortUtil(int v, LinkedList<Integer> adj[], boolean visited[], Stack stack) {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;
        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, adj, visited, stack);
        }
        // Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }
*/

}
