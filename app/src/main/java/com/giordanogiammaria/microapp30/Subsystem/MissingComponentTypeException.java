package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 12/02/2018.
 */

public class MissingComponentTypeException extends ParsingException {

    public MissingComponentTypeException(String componentType) {
        super("There is no component of type " + componentType);
    }

}
