package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 13/02/2018.
 */

public class MissingComponentException extends ParsingException {

    public MissingComponentException(String tagId, String reqId) {
        super("Component " + reqId + " required from Component " + tagId + " is missing");
    }

}
