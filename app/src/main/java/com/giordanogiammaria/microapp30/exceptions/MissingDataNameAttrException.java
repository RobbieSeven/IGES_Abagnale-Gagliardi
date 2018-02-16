package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 15/02/2018.
 */

public class MissingDataNameAttrException extends MissingAttributeException {

    public MissingDataNameAttrException(String compId, int i) {
        super("Input n. " + i + " of Component " + compId, "dataname");
    }

}
