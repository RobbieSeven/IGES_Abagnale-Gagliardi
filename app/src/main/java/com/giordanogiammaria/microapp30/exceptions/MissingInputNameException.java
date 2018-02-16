package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 14/02/2018.
 */

public class MissingInputNameException extends ParsingException {

    public MissingInputNameException(String id, String dataName) {
        super("Input of name " + dataName + " is missing for Component " + id);
    }

}
