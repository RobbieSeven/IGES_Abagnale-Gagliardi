package com.giordanogiammaria.microapp30.exceptions;


public class ComponentTypeNotFoundException extends ParsingException {


    public ComponentTypeNotFoundException(String id, String type) {
        super("Component type " + type + " is not found  for Component: " + id);
    }
}
