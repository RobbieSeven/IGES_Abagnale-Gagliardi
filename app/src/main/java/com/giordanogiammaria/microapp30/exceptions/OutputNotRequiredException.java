package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Utente on 13/02/2018.
 */

public class OutputNotRequiredException extends DataMismatchException {

    public OutputNotRequiredException(String sendId, String destId) {
        super("Component " + destId + " doesn't require outputs from Component " + sendId);
    }

}
