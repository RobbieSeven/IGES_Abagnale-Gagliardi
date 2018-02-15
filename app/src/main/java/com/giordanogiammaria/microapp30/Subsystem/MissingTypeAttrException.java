package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Utente on 15/02/2018.
 */

public class MissingTypeAttrException extends MissingAttributeException {

    public MissingTypeAttrException(String id) {
        super("Component " + id, "type");
    }

}
