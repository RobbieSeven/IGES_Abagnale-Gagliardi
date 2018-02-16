package com.giordanogiammaria.microapp30.exceptions;



public class MissingOutputException extends MissingDataException {

    public MissingOutputException(String dataType) {
        super("Missing output of type " + dataType + " for current component");
    }

}
