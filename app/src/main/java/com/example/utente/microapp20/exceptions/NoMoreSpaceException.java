package com.example.utente.microapp20.exceptions;

public class NoMoreSpaceException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoMoreSpaceException(int ind){
		super("No more space for new component. You can only use " + ind + " new component");
	}
	
}
