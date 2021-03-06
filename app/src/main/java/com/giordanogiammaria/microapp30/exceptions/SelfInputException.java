package com.giordanogiammaria.microapp30.exceptions;

public class SelfInputException extends ParsingException {

    public SelfInputException(String id, String dataName) {
        super("Component " + id + " require input of name " + dataName + " from itself");
    }

}
