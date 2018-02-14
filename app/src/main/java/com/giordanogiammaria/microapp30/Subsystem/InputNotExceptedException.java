package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 13/02/2018.
 */

public class InputNotExceptedException extends ParsingException {

    public InputNotExceptedException(String sendId, String destId, String dataName) {
        super("Input of name " + dataName + " sent by Component " + sendId + " is not required from Component " + destId);
    }

}
