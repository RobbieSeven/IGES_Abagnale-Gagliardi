package com.giordanogiammaria.microapp30.exceptions;

public class MissingDataNameAttrException extends MissingAttributeException {

    public MissingDataNameAttrException(String compId, int i) {
        super("Input n. " + i + " of Component " + compId, "dataname");
    }

}
