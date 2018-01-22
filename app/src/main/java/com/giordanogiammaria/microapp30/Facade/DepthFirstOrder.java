package com.giordanogiammaria.microapp30.Facade;

import org.jgrapht.DirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Roberto on 22/01/2018.
 */

public class DepthFirstOrder<V, E> {





    /*private ArrayList<V> marked;        // marked[v] = has v been marked in dfs?
    private int[] pre;                  // pre[v]    = preorder  number of v
    private int[] post;                 // post[v]   = postorder number of v
    private Queue<V> preorder;          // vertices in preorder
    private Queue<V> postorder;         // vertices in postorder
    private int preCounter;             // counter or preorder numbering
    private int postCounter;            // counter for postorder numbering
    private Set<V> vertices;

    *//**
     * Determines a depth-first order for the digraph {@code G}.
     * @param G the digraph
     *//*
    public DepthFirstOrder(DirectedGraph<V, E> G) {
        int V = G.vertexSet().size();
        pre = new int[V];
        post = new int[V];
        postorder = new PriorityQueue<>();
        preorder = new PriorityQueue<>();
        marked = new ArrayList<>();
        vertices = G.vertexSet();
        for (V v : vertices)
            if (!marked.contains(v))
                dfs(G, v);
        assert check();
    }

    // run DFS in digraph G from vertex v and compute preorder/postorder
    private void dfs(DirectedGraph G, V v) {
        marked.add(v);
        pre[v] = preCounter++;
        preorder.add(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.add(v);
        post[v] = postCounter++;
    }

    *//**
     * Returns the preorder number of vertex {@code v}.
     * @param  v the vertex
     * @return the preorder number of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     *//*
    public int pre(int v) {
        return pre[v];
    }

    *//**
     * Returns the postorder number of vertex {@code v}.
     * @param  v the vertex
     * @return the postorder number of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     *//*
    public int post(int v) {
        return post[v];
    }

    *//**
     * Returns the vertices in postorder.
     * @return the vertices in postorder, as an iterable of vertices
     *//*
    public Iterable<Integer> post() {
        return postorder;
    }

    *//**
     * Returns the vertices in preorder.
     * @return the vertices in preorder, as an iterable of vertices
     *//*
    public Iterable<Integer> pre() {
        return preorder;
    }

    *//**
     * Returns the vertices in reverse postorder.
     * @return the vertices in reverse postorder, as an iterable of vertices
     *//*
    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v : postorder)
            reverse.push(v);
        return reverse;
    }

    // check that pre() and post() are consistent with pre(v) and post(v)
    private boolean check() {

        // check that post(v) is consistent with post()
        int r = 0;
        for (int v : post()) {
            if (post(v) != r) {
                System.out.println("post(v) and post() inconsistent");
                return false;
            }
            r++;
        }

        // check that pre(v) is consistent with pre()
        r = 0;
        for (int v : pre()) {
            if (pre(v) != r) {
                System.out.println("pre(v) and pre() inconsistent");
                return false;
            }
            r++;
        }

        return true;
    }*/

}
