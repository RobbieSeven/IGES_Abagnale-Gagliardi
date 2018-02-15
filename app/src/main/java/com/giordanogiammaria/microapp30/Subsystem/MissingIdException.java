package com.giordanogiammaria.microapp30.Subsystem;

/**
 * Created by Roberto on 15/02/2018.
 */

public class MissingIdException extends ParsingException {

    public MissingIdException(int i) {
        super("Missing id for component n. " + i);
    }

    /*private String composeIndex(int i) {
        String index;
        if (i == 0)
            index = i + "st";
        else if (i == 1)
            index = i + "nd";
        else if (i == 2)
            index = i + "rd";
        else
            index = i + "th";
        return index;
    }*/

}
