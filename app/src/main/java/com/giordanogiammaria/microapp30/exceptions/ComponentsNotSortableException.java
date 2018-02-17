package com.giordanogiammaria.microapp30.exceptions;


public class ComponentsNotSortableException extends ParsingException {

    public ComponentsNotSortableException() {
        super("Cycles found in components execution flow");
    }

}
