package com.giordanogiammaria.microapp30.exceptions;

public class FileNotFoundException extends DeployFileException {

    public FileNotFoundException() {
        super("The selected deploy file was not found");
    }

}
