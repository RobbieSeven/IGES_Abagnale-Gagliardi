package com.giordanogiammaria.microapp30.exceptions;



public class MissingComponentTypeException extends ParsingException {

    public MissingComponentTypeException(String componentType) {
        super("There is no component of type " + componentType);
    }

}
