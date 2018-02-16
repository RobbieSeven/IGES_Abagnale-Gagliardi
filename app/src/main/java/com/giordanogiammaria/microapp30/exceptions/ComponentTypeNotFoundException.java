package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Giordano Giammaria on 16/02/2018.
 */

public class ComponentTypeNotFoundException extends ParsingException {


    public ComponentTypeNotFoundException(String id, String type) {
        super("Component type " + type + " is not found  for Component: " + id);
    }
}
