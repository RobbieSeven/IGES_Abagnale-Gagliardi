package com.example.utente.microapp20;
import org.jgrapht.graph.DefaultEdge;


public class DirEdge extends DefaultEdge {

	private static final long serialVersionUID = 1L;

	public DirEdge (){
		
	}
	
	public Object getSource(){
		return super.getSource();
	}
	
	public Object getTarget(){
		return super.getTarget();
	}
	
}
