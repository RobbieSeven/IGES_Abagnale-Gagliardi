package com.giordanogiammaria.microapp30.sorting;
import org.jgrapht.graph.DefaultEdge;


public class DirEdge extends DefaultEdge {
	
	public Object getSource(){
		return super.getSource();
	}
	
	public Object getTarget(){
		return super.getTarget();
	}

	public String toString() {
		return getSource().toString() + " ---> " + getTarget().toString();
	}
	
}
