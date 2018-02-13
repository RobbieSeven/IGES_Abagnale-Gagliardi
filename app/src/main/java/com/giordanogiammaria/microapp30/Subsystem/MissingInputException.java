package com.giordanogiammaria.microapp30.Subsystem;


public class MissingInputException extends Exception {

    public MissingInputException(String dataName) {
        super("Missing input of name " + dataName + " for next component");
    }

}
