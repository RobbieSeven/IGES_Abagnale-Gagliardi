package com.giordanogiammaria.microapp30.exceptions;

public class MissingInputNameException extends ParsingException {

    public MissingInputNameException(String id, String dataName) {
        super("Input of name " + dataName + " is missing for Component " + id);
    }

}
