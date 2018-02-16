package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 13/02/2018.
 */

public class MissingComponentException extends ParsingException {

    public MissingComponentException(String fromId, String toId) {
        super("Component " + toId + " required from Component " + fromId + " is missing");
    }

}
