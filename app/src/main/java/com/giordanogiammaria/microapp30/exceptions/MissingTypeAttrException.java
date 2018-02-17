package com.giordanogiammaria.microapp30.exceptions;

public class MissingTypeAttrException extends MissingAttributeException {

    public MissingTypeAttrException(String id) {
        super("Component " + id, "type");
    }

}
