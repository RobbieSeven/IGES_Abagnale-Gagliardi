package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Utente on 15/02/2018.
 */

public class IdAlreadyTakenException extends ParsingException {

    public IdAlreadyTakenException(String id, String type) {
        super("Id " + id + " is already taken by Component of type " + type);
    }

}
