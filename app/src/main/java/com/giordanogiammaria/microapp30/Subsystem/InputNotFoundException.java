package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Utente on 13/02/2018.
 */

public class InputNotFoundException extends DataMismatchException {

    public InputNotFoundException(String sendId, String destId, String dataName, String dataType) {
        super("Output received from Component " + sendId + " to Component " + destId + " " +
                "doesn't contain data of type " + dataType + " for input of name " + dataName);
    }

}
