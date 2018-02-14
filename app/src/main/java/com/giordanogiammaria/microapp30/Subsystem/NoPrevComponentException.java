package com.giordanogiammaria.microapp30.Subsystem;


public class NoPrevComponentException extends Exception {

    public NoPrevComponentException() {
        super("No previous component available");
    }

}
