package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 15/02/2018.
 */

public class MissingIdCompAttrException extends MissingAttributeException {

    public MissingIdCompAttrException(int i) {
        super("Component n. " + i, "id");
    }

}
