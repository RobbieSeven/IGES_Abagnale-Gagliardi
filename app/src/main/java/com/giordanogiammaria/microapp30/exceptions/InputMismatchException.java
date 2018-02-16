package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Utente on 13/02/2018.
 */

public class InputMismatchException extends DataMismatchException {

    public InputMismatchException(String sendId, String destId, String dataName) {
        super("Mismatch between \"inputSenders\" and \"inputTypes\" for Component " + destId + ": " +
                "input of name " + dataName + ", that should be sent from Component " + sendId + ", " +
                "is not expected from Component " + destId);
    }

}
