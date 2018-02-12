package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Utente on 12/02/2018.
 */

public class NonExistentComponentException extends Exception {

    public NonExistentComponentException(String componentType) {
        super("There is no component of type " + componentType);
    }

}
