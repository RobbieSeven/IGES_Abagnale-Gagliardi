package com.giordanogiammaria.microapp30.exceptions;


public class MissingIdCompAttrException extends MissingAttributeException {

    public MissingIdCompAttrException(int i) {
        super("Component n. " + i, "id");
    }

}
