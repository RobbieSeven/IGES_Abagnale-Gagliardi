package com.giordanogiammaria.microapp30.exceptions;


public class MissingAttributeException extends ParsingException {

    public MissingAttributeException(String attr, String tag) {
        super("Missing attribute " + tag + " for tag \"" + attr + "\"");
    }

}
