package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Utente on 13/02/2018.
 */

public class InputNotRequiredException extends DataMismatchException {

    public InputNotRequiredException(String sendId, String destId) {
        super("Component " + destId + " doesn't require input from Component " + sendId);
    }

    public InputNotRequiredException(String sendId, String destId, String dataName) {
        super("Input of name " + dataName + " sent by Component " + sendId + " is not required from Component " + destId);
    }

}
