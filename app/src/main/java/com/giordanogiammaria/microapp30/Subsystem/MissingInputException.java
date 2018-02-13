package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 01/02/2018.
 */

public class MissingInputException extends MissingDataException {

    public MissingInputException(String dataName, String dataType) {
        super("Missing input of name " + dataName + " of type " + dataType + " for next component");
    }

}
