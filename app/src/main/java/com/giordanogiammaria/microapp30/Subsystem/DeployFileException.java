package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 15/02/2018.
 */

public class DeployFileException extends Exception {

    public DeployFileException(String message) {
        super("DEPLOY FILE ERROR:\n" + message);
    }

}
