package com.giordanogiammaria.microapp30.exceptions;


public class MissingIdInputAttrException extends MissingAttributeException {

    public MissingIdInputAttrException(String compId, int i) {
        super("Input n. " + i + " of Component " + compId, "id");
    }

}
