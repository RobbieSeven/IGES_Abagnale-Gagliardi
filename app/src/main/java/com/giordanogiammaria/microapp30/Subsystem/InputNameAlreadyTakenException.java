package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Utente on 15/02/2018.
 */

public class InputNameAlreadyTakenException extends ParsingException {

    public InputNameAlreadyTakenException(String id, String dataName) {
        super("Component " + id + " has already taken input of name " + dataName);
    }

}
