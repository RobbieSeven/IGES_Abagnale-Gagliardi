package com.giordanogiammaria.microapp30.exceptions;


public class MissingDataException extends Exception {

    public MissingDataException(String message) {
        super("MISSING DATA:\n" + message + "\nPlease insert expected data to continue");
    }

}
