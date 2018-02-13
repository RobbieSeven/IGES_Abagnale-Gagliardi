package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 13/02/2018.
 */

public class MissingComponentException extends ParsingException {

    public MissingComponentException(String fromId, String toId) {
        super("Component " + fromId + " required from Component " + toId + " is missing");
    }

}
