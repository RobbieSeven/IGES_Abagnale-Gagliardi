package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 01/02/2018.
 */

public class MissingInputException extends Exception {

    public MissingInputException(String dataName) {
        super("Missing input of name " + dataName + " for next component");
    }

}
