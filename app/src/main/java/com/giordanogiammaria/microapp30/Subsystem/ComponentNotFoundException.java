package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 13/02/2018.
 */

public class ComponentNotFoundException extends Exception {

    public ComponentNotFoundException(String id) {
        super("Component " + id + " not found");
    }

}
