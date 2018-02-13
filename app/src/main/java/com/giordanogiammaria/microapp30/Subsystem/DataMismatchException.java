package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Utente on 13/02/2018.
 */

public class DataMismatchException extends Exception {

    public DataMismatchException(String message) {
        super("ERROR\n" + message + "\nThe microapp has been terminated");
    }

}
