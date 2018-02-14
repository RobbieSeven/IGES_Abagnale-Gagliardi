package com.giordanogiammaria.microapp30.Subsystem;



public class MissingOutputException extends MissingDataException {

    public MissingOutputException(String dataType) {
        super("Missing output of type " + dataType + " for current component");
    }

}
