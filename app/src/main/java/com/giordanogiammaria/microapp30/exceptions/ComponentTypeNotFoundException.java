package com.giordanogiammaria.microapp30.exceptions;


public class ComponentTypeNotFoundException extends ParsingException {
    public ComponentTypeNotFoundException(String id, String type) {
        super(id+type);
    }
}
