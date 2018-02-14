package com.giordanogiammaria.microapp30.Subsystem;


public class MissingInputException extends MissingDataException {

    public MissingInputException(String dataName, String dataType) {
        super("Missing input of name " + dataName + " of type " + dataType + " for next component");
    }

}
