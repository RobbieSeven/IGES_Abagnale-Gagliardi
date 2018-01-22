package com.giordanogiammaria.microapp30;


import com.giordanogiammaria.microapp30.Subsystem.DirEdge;

import org.jgrapht.DirectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DepthFirstOrder<V, E> {
	
	private ArrayList<V> reversePost = new ArrayList<V>();
	private Set<V> visited;

	public DepthFirstOrder(DirectedGraph<V, DirEdge> grafo, V v) {
		visited = new HashSet<V>();
		dfs(grafo, v);
		Set<V> set = grafo.vertexSet();
		int size = set.size();
		Object[] nodes = new Object[size];
		set.toArray(nodes);
		for (int i = 0; i < size; i++)
			if (!visited.contains(nodes[i])){
				dfs(grafo, (V) nodes[i]);
			//	firstComp.add((V) nodes[i]);
			}
	}

	private void dfs(DirectedGraph<V, DirEdge> grafo, V v) {
		if (grafo.inDegreeOf(v)>0) return;
		visited.add(v);
		reversePost.add(v);
		Set<DirEdge> set = grafo.outgoingEdgesOf(v);
		int size = set.size();
		DirEdge[] edges = new DirEdge[size]; 
		grafo.outgoingEdgesOf(v).toArray(edges);
		for (int i = 0; i < size; i++) {
			V node = (V) edges[i].getTarget();
			if (!visited.contains(node)) 
				grafo.removeEdge(edges[i]);
			dfs(grafo, node);
		}
		//grafo.removeVertex(v);//aggiunto ora
	}
	
	public ArrayList<V> reversePost() {
		return reversePost;
	}
	
/*	public ArrayList<V> firstComp()
	{ return firstComp; }
	*/
	
}
