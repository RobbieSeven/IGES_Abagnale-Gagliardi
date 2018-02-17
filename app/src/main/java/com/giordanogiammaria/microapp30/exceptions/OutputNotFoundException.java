package com.giordanogiammaria.microapp30.exceptions;

public class OutputNotFoundException extends DataMismatchException {

    public OutputNotFoundException(String sendId, String destId, String dataName, String dataType) {
        super("Output received from Component " + sendId + " to Component " + destId + " " +
                "doesn't contain data of type " + dataType + " for input of name " + dataName);
    }

}
