package com.giordanogiammaria.microapp30.exceptions;

public class InputNameAlreadyTakenException extends ParsingException {

    public InputNameAlreadyTakenException(String id, String dataName) {
        super("Component " + id + " has already taken input of name " + dataName);
    }

}
