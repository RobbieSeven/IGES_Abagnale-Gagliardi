package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 15/02/2018.
 */

public class SelfInputException extends ParsingException {

    public SelfInputException(String id, String dataName) {
        super("Component " + id + " require input of name " + dataName + " from itself");
    }

}
