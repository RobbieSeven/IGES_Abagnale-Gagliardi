package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 13/02/2018.
 */

public class ParsingException extends Exception {

    public ParsingException(String message) {
        super("ERROR FOUND IN DEPLOY FILE:\n" + message + "\nPlease check the selected deploy file and try again");
    }

}
