package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 13/02/2018.
 */

public class MissingDataException extends Exception {

    public MissingDataException(String message) {
        super("MISSING DATA:\n" + message + "\nPlease insert expected data to continue");
    }

}
