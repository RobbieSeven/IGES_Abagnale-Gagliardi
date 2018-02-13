package com.giordanogiammaria.microapp30.Subsystem;



public class NonExistentComponentException extends Exception {

    public NonExistentComponentException(String componentType) {
        super("There is no component of type " + componentType);
    }

}
