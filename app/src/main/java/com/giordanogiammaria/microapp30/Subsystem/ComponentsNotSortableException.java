package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 15/02/2018.
 */

public class ComponentsNotSortableException extends ParsingException {

    public ComponentsNotSortableException() {
        super("Cycles found in components execution flow");
    }

}
