package com.giordanogiammaria.microapp30.exceptions;


public class EmptyDeployFileException extends DeployFileException {

    public EmptyDeployFileException() {
        super("The selected deploy file was empty");
    }

}
