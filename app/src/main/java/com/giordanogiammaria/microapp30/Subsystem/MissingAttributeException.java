package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 15/02/2018.
 */

public class MissingAttributeException extends ParsingException {

    public MissingAttributeException(String tag, String attr) {
        super("Missing attribute " + attr + " for tag \"" + tag + "\"");
    }

}
