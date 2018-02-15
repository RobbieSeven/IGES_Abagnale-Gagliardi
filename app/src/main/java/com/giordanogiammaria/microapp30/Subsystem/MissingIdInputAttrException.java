package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 15/02/2018.
 */

public class MissingIdInputAttrException extends MissingAttributeException {

    public MissingIdInputAttrException(String compId, int i) {
        super("Input n. " + i + " of Component " + compId, "id");
    }

}
