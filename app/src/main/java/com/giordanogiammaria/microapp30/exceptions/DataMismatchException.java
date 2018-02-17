package com.giordanogiammaria.microapp30.exceptions;



public class DataMismatchException extends Exception {

    public DataMismatchException(String message) {
        super("ERROR\n" + message + "\nThe microapp has been terminated");
    }

}
