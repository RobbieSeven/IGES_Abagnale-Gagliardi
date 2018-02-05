package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Utente on 01/02/2018.
 */

public class NoPrevComponentException extends Exception {

    public NoPrevComponentException() {
        super("No previous component available");
    }

}
