package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 01/02/2018.
 */

public class MissingOutputException extends Exception {

    public MissingOutputException(String dataType) {
        super("Missing output of type " + dataType + "for current component");
    }

}
