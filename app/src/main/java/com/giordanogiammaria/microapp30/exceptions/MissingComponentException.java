package com.giordanogiammaria.microapp30.exceptions;


public class MissingComponentException extends ParsingException {

    public MissingComponentException(String fromId, String toId) {
        super("Component " + toId + " required from Component " + fromId + " is missing");
    }

}
