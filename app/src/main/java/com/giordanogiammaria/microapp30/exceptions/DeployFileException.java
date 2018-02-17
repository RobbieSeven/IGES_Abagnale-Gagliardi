package com.giordanogiammaria.microapp30.exceptions;

public class DeployFileException extends Exception {

    public DeployFileException(String message) {
        super("DEPLOY FILE ERROR:\n" + message);
    }

}
