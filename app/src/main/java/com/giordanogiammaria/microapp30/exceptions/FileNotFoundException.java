package com.giordanogiammaria.microapp30.exceptions;

/**
 * Created by Roberto on 15/02/2018.
 */

public class FileNotFoundException extends DeployFileException {

    public FileNotFoundException() {
        super("The selected deploy file was not found");
    }

}
