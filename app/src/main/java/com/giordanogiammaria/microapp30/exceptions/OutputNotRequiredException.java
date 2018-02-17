package com.giordanogiammaria.microapp30.exceptions;

public class OutputNotRequiredException extends DataMismatchException {

    public OutputNotRequiredException(String sendId, String destId) {
        super("Component " + destId + " doesn't require outputs from Component " + sendId);
    }

}
