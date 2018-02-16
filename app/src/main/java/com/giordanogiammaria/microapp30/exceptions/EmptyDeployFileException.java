package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 15/02/2018.
 */

public class EmptyDeployFileException extends DeployFileException {

    public EmptyDeployFileException() {
        super("The selected deploy file was empty");
    }

}
